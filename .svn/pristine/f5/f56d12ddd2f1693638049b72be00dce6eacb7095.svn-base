/**
 * 
 */
package com.flyrish.hades.webapp.action;

import org.nestframework.commons.hibernate.IPage;

/**
 * @author audin
 * 
 */
public abstract class PageAction<E> extends BaseAction {
	public int pageSize = 15;

	public int pageNo = 1;

	public String orderBy;

	public String orderType;

	protected IPage<E> pageObj;

	/**
	 * @return the pageObj
	 */
	public IPage<E> getPageObj() {
		return pageObj;
	}

	// 处理翻页序号显示的问题
	public void correntPageNo() {
		if (null != pageObj) {
			int maxPage = pageObj.getTotalCount() / pageObj.getPageSize();
			int modeNum = pageObj.getTotalCount() % pageObj.getPageSize();
			if (modeNum != 0) {
				maxPage = maxPage + 1;
			}
			if (pageNo > maxPage) {
				pageNo = maxPage;
			}
			if (pageNo < 1) {
				pageNo = 1;
			}
		}
	}
}
