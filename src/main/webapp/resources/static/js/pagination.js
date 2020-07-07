
var Pagenation = function (url,pageInfo,params){
	this.pageInfo = pageInfo;
	this.params = params;
	this.url = url;

	this.getPagenationEl = function(){
		var curPage = this.pageInfo.curPage;
		var curRange = this.pageInfo.curRange;
		var queryString = gfn_setQueryParam(this.params);

		var startPage = Number(this.pageInfo.startPage);
		var endPage = Number(this.pageInfo.endPage);

		var prevBtn,nextBtn = "";
		if(curPage != 1){
			prevBtn ='<li class="page-item"><a class="page-link prev" href="/#">Prev</a></li>';
		}
		if(this.pageInfo.pageCnt > 0 && curPage != this.pageInfo.pageCnt){
			nextBtn = '<li class="page-item"><a class="page-link next" href="/#">Next</a></li>';
		}
		var html = '<nav aria-label="Page navigation"><ul class="pagination">';
		for(var i=startPage; i<=endPage; i++){
			var activeClass = curPage == i ? "active" : ""
			html+= '<li class="page-item '+activeClass+'"><a class="page-link page" href="/#">'+i+'</a></li>';
		}

		html += '</ul></nav>';
		$(".pagenation-container").empty().append(html);
		$(".pagination").append(nextBtn);
		$(".pagination").prepend(prevBtn);
	}

	this.eventBind = function(){
		var url = this.url;
		$(".page-link").click(function(e){
			e.preventDefault();
		});

		$(".page-link.next").click(function(e){
			this.params.page = this.pageInfo.nextPage;
			url += gfn_setQueryParam(this.params);
			location.href = url;
		}.bind(this));
		$(".page-link.prev").click(function(e){
			this.params.page = this.pageInfo.prevPage;
			url += gfn_setQueryParam(this.params);
			location.href = url;
		}.bind(this));
		$(".page-link.page").click(function(e){
			this.params.page = Number($(e.target).text());
			console.log("param",this.params);
			url += gfn_setQueryParam(this.params);
			location.href = url;
		}.bind(this));
	}

	this.init = function(){
		this.getPagenationEl();
		this.eventBind();
	}

	this.init();
}

