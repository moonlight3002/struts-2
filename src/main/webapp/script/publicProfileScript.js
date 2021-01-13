/**
 * 
 */

$('.followBtn').click(function(){
	var followUrl;
	var followAction = $(this).data("action");
	if (followAction=="follow"){
		followUrl = "followUser.action";
		setFollow(followUrl, function(data){
			$(`.follow-status[data-status=not-following]`).hide();
			$(`.follow-status[data-status=following]`).show();
		});
	}else if (followAction =="unfollow"){
		followUrl = "unfollowUser.action";
		setFollow(followUrl, function(data){
			$(`.follow-status[data-status=following]`).hide();
			$(`.follow-status[data-status=not-following]`).show();
		});
	}
})

$('.follow-status[data-status="following"]').click(function(){
	$('.unfollow').fadeToggle();
})

function getFollowData(method){
	var obj={}
	var followData={}
	followData['followingUserID'] = userProfileID;
	obj['follow'] = followData;
	console.log(obj);
	return obj;
}
	
function setFollow(followUrl, callback){
	
	$.ajax({
		type : "POST",
		data : JSON.stringify(getFollowData()),
		contentType : 'application/json',
		url : followUrl,
		success : function(data) {
			console.log(data);
			if (data>0){
				callback(data)
			}

		},
		error: function(data){
		}
	})
}