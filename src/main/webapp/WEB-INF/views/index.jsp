<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/static/css/main.css" media="all">
<script type="text/javascript" src="/resources/static/lib/jquery-1.10.2.js"></script>
<script
  type="text/javascript"
  src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8af929032ee6b7d4a33b27735eb565fa"
></script>
    <div class="wrapper">
      <nav>
        <ul class="navi">
          <li class="active" data-idx="0">
            Coming soon
          </li>
          <li data-idx="1">
            Contact us
          </li>
        </ul>
      </nav>
      <section class="container">
        <h1 class="logo"><img src="/resources/static/images/img_logo.png" alt="시아나 로고" /></h1>
        <div class="content-wrap">
          <div class="content active" data-idx="0">
            <p class="text-01">
              세상과 연결되는 그곳!<br />
              시아나가 새로운 얼굴로 서 있겠습니다.
            </p>
            <div class="text-box">
              <p class="text-02">
                항상 시아나를 아껴주시는 고객 여러분들의 성원에 힘입어, 2020년 8월에 새롭게 도약하는
                시아나 홈페이지가 리뉴얼 오픈 준비중에 있습니다.
              </p>
              <p class="text-03">
                새로운 홈페이지와 함께 고객 여러분들에게 보다 나은 서비스를 제공할 수 있도록
                노력하겠습니다.
              </p>
            </div>
          </div>
          <div class="content" data-idx="1">
            <div class="left">
              <ul class="info-list">
                <li>
                  <strong class="tit">사업 본부</strong>
                  <p class="txt"><a href="mailto:siwan@sianacorp.com">siwan@sianacorp.com</a></p>
                </li>
                <li>
                  <strong class="tit">전략기획 본부</strong>
                  <p class="txt">
                    <a href="mailto:jychoap@sianacorp.com">jychoap@sianacorp.com</a>
                  </p>
                </li>
                <li>
                  <strong class="tit">개발본부</strong>
                  <p class="txt"><a href="mailto:hh.lee@sianacorp.com">hh.lee@sianacorp.com</a></p>
                </li>
                <li>
                  <strong class="tit">Front-end 개발팀</strong>
                  <p class="txt"><a href="mailto:bsbae@sianacorp.com">bsbae@sianacorp.com</a></p>
                </li>
                <li>
                  <strong class="tit">UX/UI 디자인 본부</strong>
                  <p class="txt"><a href="mailto:seoha@sianacorp.com">seoha@sianacorp.com</a></p>
                </li>
              </ul>
            </div>
            <div class="right">
              <p class="map-tit"><strong>본사</strong>서울시 강남구 강남대로 118길 20, 4층</p>
              <div class="map-area">
                <div class="map-bg"></div>
                <div id="map" style="width: 100%; height: 100%;"></div>
              </div>
            </div>
          </div>
        </div>
        <address>
          SIANA corp Co.,Ltd. | T. 82.2.6205.3151 | F. 82.2.6205.3154 | 4F, 20, Gangnam-daero
          118-gil, Gangnam-gu, Seoul, Republic of Korea
        </address>
      </section>
    </div>
    <!--  // .wrapper End -->

    <script>
    var cnt = 0;
    $(function(){
    	fn_pageInit()
    })
      function fn_pageInit(){
     	  setKakaoMap()
          bindEvent()
	   	  setInterval(function(){
			cnt = 0;
		  }, 3000);
      }

      var map, center
      function setKakaoMap(isFirst) {
        /*KAKAOMAP*/
        var container = document.getElementById("map") //지도를 담을 영역의 DOM 레퍼런스
        center = new kakao.maps.LatLng(37.506576, 127.024975)
        var options = {
          //지도를 생성할 때 필요한 기본 옵션
          center: center, //지도의 중심좌표.
          level: 3, //지도의 레벨(확대, 축소 정도)
        }
        var marker = new kakao.maps.Marker({ position: options.center })
        map = new kakao.maps.Map(container, options)
        marker.setMap(map)
      }

      function bindEvent() {
        var firstFlag = true
        $(".navi li").click(function (e) {
          if ($(this).hasClass("active") && !firstFlag) return false
          $(e.target).addClass("active").siblings().removeClass("active")
          var index = $(e.target).attr("data-idx")
          $(".content").removeClass("active")
            $(".content[data-idx=" + index + "]").addClass("active")
            if (index == 1) {
              map.relayout()
              map.setCenter(center)
            }
          firstFlag = false
        })
        $(".navi li").eq(0).click()

        $("address").click(function(){
	   		cnt++;
	   		if(cnt == 5){
	   			location.href="/servlet/bbs/login";
	   		}
	   	})
      }
    </script>