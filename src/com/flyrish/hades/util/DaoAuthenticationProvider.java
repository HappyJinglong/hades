package com.flyrish.hades.util;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.dao.UserCache;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.acegisecurity.providers.encoding.PlaintextPasswordEncoder;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.flyrish.hades.dto.AuthInfo;
import com.flyrish.hades.service.ext.IUserDetailsServiceExt;

/**
 * An {@link AuthenticationProvider} implementation that retrieves user details
 * from an {@link UserDetailsService}.
 * 
 * @author Ben Alex
 * @version $Id: DaoAuthenticationProvider.java 1496 2006-05-23 13:38:33Z
 *          benalex $
 */
public class DaoAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {
	// ~ Instance fields
	// ================================================================================================

	private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

	private SaltSource saltSource;

	private IUserDetailsServiceExt userDetailsService;

	// ~ Methods
	// ========================================================================================================

	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		Object salt = null;

		if (this.saltSource != null) {
			salt = this.saltSource.getSalt(userDetails);
		}

		if (!passwordEncoder.isPasswordValid(userDetails.getPassword(),
				authentication.getCredentials().toString(), salt)) {
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"), userDetails);
		}
	}

	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService,
				"An Authentication DAO must be set");
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public SaltSource getSaltSource() {
		return saltSource;
	}

	public IUserDetailsServiceExt getUserDetailsService() {
		return userDetailsService;
	}

	protected final UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser = null;
		try {
			if (authentication.getDetails() instanceof AuthInfo) {
				AuthInfo authInfo = (AuthInfo) authentication.getDetails();
					loadedUser = this.getUserDetailsService().loadUserByUsername(authInfo.getUsername(), authInfo.getPassword(),authInfo.getSchoolId()
							,authInfo.getUsertype(),authInfo.getUserid(),authInfo.getSystemtype());
			}
		} catch (DataAccessException repositoryProblem) {
			throw new AuthenticationServiceException(repositoryProblem
					.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new AuthenticationServiceException(
					"AuthenticationDao returned null, which is an interface contract violation");
		}

		return loadedUser;
	}

	/**
	 * Sets the PasswordEncoder instance to be used to encode and validate
	 * passwords. If not set, {@link PlaintextPasswordEncoder} will be used by
	 * default.
	 * 
	 * @param passwordEncoder
	 *            The passwordEncoder to use
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * The source of salts to use when decoding passwords. <code>null</code>
	 * is a valid value, meaning the <code>DaoAuthenticationProvider</code>
	 * will present <code>null</code> to the relevant
	 * <code>PasswordEncoder</code>.
	 * 
	 * @param saltSource
	 *            to use when attempting to decode passwords via the
	 *            <code>PasswordEncoder</code>
	 */
	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	public void setUserDetailsService(IUserDetailsServiceExt authenticationDao) {
		this.userDetailsService = authenticationDao;
	}
}

