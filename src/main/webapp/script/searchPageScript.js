/**
 * 
 */

var userFilterOffset = 15;

$(function(){
	
	$('.mynav-input-search').on('blur', function(){

		$(this).val(`${defaultInputValue}`);
	})
	
	$('.filterBtn').on('click', function(){
		$('.sideBar-left').removeClass('d-sm-none d-none');
		$('.sideBar-left').addClass('d-sm-block col-sm-3 col-6').hide().effect('slide', { direction: "left" }, 200);
	})
	
	$('.closeFilterBtn').on('click', function(){
		$('.sideBar-left').removeClass('d-sm-block col-sm-3 col-6');
		$('.sideBar-left').addClass('d-sm-none d-none');
	})
	
	$('.skill-filter-item-input').on('change', function(){
		console.log("change")
		userFilterOffset =0;
		$('.content-search-user').find('.content-search-user-item:not(.newItem').fadeOut(500).remove();
		refreshSearchUser(userFilterOffset);
	})
	
	
	$(window).scroll(function() {
		   if($(window).scrollTop() + $(window).height() > ($('.seemore').offset().top)) {
		       console.log("bottom!");
		       if($('.seemore').hasClass("active")){
		    	   refreshSearchUser(userFilterOffset);	
		       }
		   }
		});
	
	
	
})



function getFilterUserData(offset){
	var obj={}
	var technologyids = []
	obj['inputValue'] = defaultInputValue;
	obj['searchOffset'] = offset;
	$('.skill-filter-item-input').each(function(){
		if($(this).prop("checked")==true){
			technologyids.push($(this).data("id"));
		}
		
	})
	obj['technologyids'] = technologyids
	console.log(obj);
	return obj;
}

function refreshSearchUser(offset){
	$(`.seemore`).removeClass("active");
	$.ajax({
		type : "POST",
		url : "searchMore.action",
		data : JSON.stringify(getFilterUserData(offset)),
		contentType : 'application/json',
		success : function(datas) {
			if(datas.length >0){
				console.log("userdatas:",datas);
				appendUser(datas);
				if(datas.length<15){
					$(`.seemore`).removeClass('active').fadeOut(2000, function(){
						   $(`.nomore`).fadeIn(100);
					   });
				}
			}else{
				$(`.seemore`).removeClass('active').fadeOut(2000, function(){
					   $(`.nomore`).fadeIn(100);
				   });
			}
			if(datas.length<15){
				$(`.seemore`).removeClass('active').fadeOut(2000, function(){
					   $(`.nomore`).fadeIn(100);
				   });
			}
			
		}
	})
}

function appendUser(datas){
	$.each(datas, function(i, item){
		userFilterOffset +=1;
		$cloneItem = $(`.content-search-user-item.newItem`).clone();
		$cloneItem.attr("href", `${datas[i].userName}`);
		$cloneItem.find('img').attr("src", `getFile?userID=${datas[i].userID}&profilePic=${datas[i].profilePic}`);
		var defaultprofilePic = "defaultpp_"+datas[i].firstName.substring(0,1).toUpperCase();
		$cloneItem.find('img').attr("onerror",`this.onerror=null;this.src="\\/ID0420FF19OWidya\\/assets\\/${defaultprofilePic}.jpg"`);
		$cloneItem.find('.search-user-fullname>p').text(`${datas[i].firstName} ${datas[i].lastName}` );
		if (datas[i].bio!=null){
			$cloneItem.find('.search-user-bio>p').text(`${datas[i].bio}`);
		}

		$cloneItem.removeClass("newItem");
		$cloneItem.insertBefore(`.seemore`).hide().fadeIn();
		
	})
	$(`.seemore`).addClass("active");
}




//	content-search-user-item


//var defaultprofilePic = "defaultpp_"+datas[i].firstName.substring(0,1).toUpperCase();
//			$('.mynav-search-dropdown').prepend(`<a href='${datas[i].userName}' class='nav-link mynav-search-user'><img src='${datas[i].profilePicBase64}' onerror='this.onerror=null;this.src="\\/ID0420FF19OWidya\\/assets\\/${defaultprofilePic}.jpg"' class='circle'><span class='fullName'>${datas[i].firstName} ${datas[i].lastName}</span></a>`);*/