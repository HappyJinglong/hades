<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:if test="${usertype ne ADMIN and usertype ne CONSTY and userRealtype ne SPORT and userRealtype ne SCHOOLADMIN}">
       <h3 class="con_title">业务提示</h3>
       <c:if test="${userRealtype eq STUDENT}">
            
              <li>新学期伊始的我&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期开始后1个月内完成</li>
              <li>学期结束时的我&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期结束后1周内完成</li>
              <li>思想道德<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
             <c:if test="${levelcode eq '2012002' }"> 
              <li>学业成就->自我评价<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              <li>学业成就->学科作品展示学期结束后1周内完成</li>
             </c:if> 
             <c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
              <li>学业成就->自我评价<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              <li>学业成就->学科学习过程记录  学期结束后1周内完成</li>
             </c:if>
              <li>合作与交流<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              <li>运动与健康<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span> </li>
              <li>审美与表现<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成 </span></li>
              <li>综合实践活动&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 新学期开学后1个月内完成</li>
              <li>个性发展<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
           </c:if>
           <c:if test="${userRealtype eq PARENT}">
            <c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
             <li>新学期家长的期望&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期开始后1个月内完成</li>
            </c:if>
              <li>学期结束家长的评语和期望  学期开始后1个月内完成</li>
              <li>思想道德<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成 </span></li>
              <li>学业成就<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              <li>合作与交流<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              <li>运动与健康<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span> </li>
              <li>审美与表现<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成 </span></li>
              <li>个性发展<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
       </c:if>
       <c:if test="${userRealtype eq MASTER}">
              <li>班主任评语&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期结束后1周内完成</li>
              <li>思想道德<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成 </span></li>
              <c:if test="${levelcode eq '2012002' }"> 
                 <li>课程评语&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期结束后1周内完成</li>
              </c:if> 
              <c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
                  <li>学业成就<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              </c:if>
              <li>合作与交流<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              <li>运动与健康<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span> </li>
              <li>审美与表现<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成 </span></li>
              <li>个性发展<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
       </c:if>
       <c:if test="${userRealtype eq COURSE}">
              <li>思想道德<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成 </span></li>
              <li>课程评语&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期结束后1周内完成</li>
              <c:if test="${levelcode eq '2012003' or levelcode eq '2012004' }">
                  <li>学业成就<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              </c:if>
              <li>合作与交流<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
              <li>运动与健康<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span> </li>
              <li>审美与表现<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成 </span></li>
              <li>个性发展<span class="fr">第1学期在11月底前完成，第2学期在5月底前完成</span></li>
       </c:if>
     </c:if>
     <c:if test="${usertype eq ADMIN or usertype eq CONSTY or userRealtype eq SPORT or userRealtype eq SCHOOLADMIN}">
         <h3 class="con_title">待办事项</h3>
     </c:if>
