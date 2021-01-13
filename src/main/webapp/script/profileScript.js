$(function() {
			
			var dob = $('#dob>p').text().split("/");
			
			
			
			
			var editdob = dob[0] + "-" + fullmonths[dob[1]] + "-" + dob[2];
			
			$('#dob>p').text(`${editdob}`);
			
			$('.month').each(function(){
				editMonth($(this));
			});
			

			
			$('.endYear').each(function(){
				editYear($(this));
			});
			


			var followerOffset = 0;
			var followingOffset = 0;
			
			$('#follower').click(function(){
				$(`.seemore`).removeClass("onScreen");
				$(`.seemore[data-action="Follower"]`).addClass("onScreen");
				console.log("length:", $(`.Follower.follow-items > .follow-item:not(.newItem)`).length);
				$('.profile-content').hide().removeClass('active');
			    $('.menuDiv').find('.active').removeClass('active');
			    $(this).addClass('active');
			    $('.profile-content.Follower').fadeIn({easing:"swing"}).addClass('active');
			    if($(`.Follower.follow-items > .follow-item:not(.newItem)`).length <1){
				    getFollowData("Follower");
			    }

			})
			
			$('#following').click(function(){
				$(`.seemore`).removeClass("onScreen");
				$(`.seemore[data-action="Following"]`).addClass("onScreen");
				
			    console.log("length:", $(`.Follower.follow-items > .follow-item:not(.newItem)`).length);
				$('.profile-content').hide().removeClass('active');
			    $('.menuDiv').find('.active').removeClass('active');
			    $(this).addClass('active');
			    $('.Following').fadeIn({easing:"linear"}).addClass('active');
			    if($(`.Following.follow-items > .follow-item:not(.newItem)`).length <1){
			    	getFollowData("Following");
			    }
			})
			
			$('#aboutme').click(function(){
				$(`.seemore`).removeClass("onScreen");
			    $('.profile-content').hide().removeClass('active');
			    $('.menuDiv').find('.active').removeClass('active');
			    $(this).addClass('active');
			    $('.profile-content.Aboutme').fadeIn({easing:"swing"}).addClass('active');
			})
			
			$(window).scroll(function() {
			   if($(window).scrollTop() + $(window).height() > ($('.seemore').offset().top)) {
			       console.log("bottom!");
			       if($('.seemore').hasClass("onScreen") && $('.seemore').hasClass("active")){
			       getFollowData($('.seemore.onScreen').data("action"));	
			       }
			   }
			});
			
			$('input.follow-search').keyup(function(){
				var followClass= $(this).data("action");
				$(`.profile-content.${followClass}`).find('.follow-item:not(.newItem)').remove();
				if(followClass == "Follower"){
					followerOffset = 0;
				}else if (followClass == "Following"){
					followingOffset = 0;
				}
				getFollowData($(this).data("action"));	
				
			    
			})
			
			$(window).scroll(function() {
			   if($(window).scrollTop() + $(window).height() > ($('.seemore').offset().top)) {
			       console.log("bottom!");
			       if($('.seemore').hasClass("onScreen") && $('.seemore').hasClass("active")){
			       getFollowData($('.seemore.onScreen').data("action"));	
			       }
			   }
			});
			
			function appendFollow(followdatas,followClass){
				console.log(followClass);
				$.each(followdatas, function(i, item){
					if(followClass == "Follower"){
						followerOffset += 1;
					}else if (followClass == "Following"){
						followingOffset += 1;
					}	
					$cloneItem = $(`.profile-content.${followClass}`).find('.newItem').clone();
					$cloneItem.find('a').attr("href", `${followdatas[i].userName}`);
					$cloneItem.find('img').attr("src", `http://localhost:8080/ID0420FF19OWidya/getFile?userID=${followdatas[i].userID}&profilePic=${followdatas[i].profilePic}`);
					var defaultprofilePic = "defaultpp_"+followdatas[i].firstName.substring(0,1).toUpperCase();
					$cloneItem.find('img').attr("onerror",`this.onerror=null;this.src="\\/ID0420FF19OWidya\\/assets\\/${defaultprofilePic}.jpg"`);
					$cloneItem.find('.follow-name').append(`${followdatas[i].firstName} ${followdatas[i].lastName}` );
					$cloneItem.find('.follow-follower').append(`${followdatas[i].followerCount} followers`);
					$cloneItem.removeClass("newItem");
					$cloneItem.appendTo(`.follow-items.${followClass}`).hide().fadeIn();
					
				})
				$(`.seemore[data-action=${followClass}]`).addClass("active");
			}
			
			function getFilterFollowData(followClass){
				if(followClass == "Follower"){
					followerOffset = followerOffset;
				}else if (followClass == "Following"){
					followingOffset = followingOffset;
				}	
				var obj={}
				var userData = {}
				userData['userID'] = userProfileID;
				obj['offset'] = (followClass=="Follower") ? followerOffset : followingOffset;
				console.log(obj.offset);
				obj['inputValue'] = $(`input.${followClass}`).val();
				obj['user'] = userData;
				console.log(obj);
				return obj;
			}
			
			function getFollowData(followClass){
				$('.seemore').removeClass("active");
				var urlaction = ""
				if(followClass == "Follower"){
					urlaction = "searchFollower.action";
				}else if(followClass == "Following"){
					
					urlaction = "searchFollowing.action";
				}
				$.ajax({
					type : "POST",
					url : urlaction,
					data : JSON.stringify(getFilterFollowData(followClass)),
					contentType : 'application/json',
					success : function(datas) {
						if(datas.length >0){
							console.log("followdatas:",datas);
							appendFollow(datas,followClass);
						}else{
							$(`.seemore[data-action=${followClass}]`).fadeOut(2000, function(){
								   $(`.nomore[data-action=${followClass}]`).fadeIn(100);
							   });
						}
						
					}
				})
				
			}
			

})
var months = ['Present', 'Jan', 'Feb', 'March', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
			var fullmonths = {
					'01': 'January',
					'02': 'February',
					'03': 'March',
					'04': 'Apr',
					'05': 'May',
					'06': 'June',
					'07': 'July',
					'08': 'August',
					'09': 'September',
					'10': 'October',
					'11': 'November',
					'12': 'December',
					
			}
			function editMonth(elem){
				var editMonthValue = elem.text();
				console.log(editMonthValue);
				if (editMonthValue==null){
					editMonthValue =0
				}
				elem.text(`${months[editMonthValue]}`);
				console.log(months[editMonthValue]);
			}
			
			function editYear(elem){
				var editYear = elem.text();
				if(editYear == 0){
					elem.empty();
				}
			}
			



