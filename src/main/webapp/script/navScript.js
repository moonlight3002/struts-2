/**
 * 
 */

$(function() {
	$('.mynav-search-dropdown').empty();
	var searchContainer= "";
	var data = {};
	$('#search').focus(function(){
		if($(this).val()!= ""){
			$('.mynav-search-dropdown').show();
		}
	});
	$('#search').blur(function(){
		$('.mynav-search-dropdown').fadeOut();
	});
	
	var timer;
	$('#search').keyup(function(e) {
			clearTimeout(timer);
			console.log("keyup");
			var inputValue = $(this).val();
			console.log(inputValue);
			$('.mynav-search-dropdown').empty();
			if(inputValue !=""){
				$('.mynav-search-dropdown').show();
				timer = setTimeout(function(){
					searchUser(inputValue);
				},200);
			}else{
				$('.mynav-search-dropdown').fadeOut();
			}
			
		
	});
	
		$('#searchButton').click(function(){
			if($('#search').val()!=""){
				var inputValue = $('#search').val();
				window.location.href = `search?inputValue=${inputValue}`;
			}
			
			
		})
	
	

	function searchUser(inputValue) {
		$.ajax({
			type : "POST",
			url : "http://localhost:8080/ID0420FF19OWidya/quickSearch.action",
			data : {
				'inputValue': inputValue},
			success : function(datas) {
				console.log(datas['inputValue']);
				console.log(datas['userCollections'])
				console.log(datas['userCollections'].length)
				
				showdata(datas['userCollections'], inputValue);
			}
		})
	}
	
	function showdata(datas, inputValue){
		if((typeof datas === 'undefined') || (datas.length == 0)){
			$('.mynav-search-dropdown').empty();
			$('.mynav-search-dropdown').append("<a class='nav-link mynav-search-user'><span class='noresult'>no result found</span></a>")
		}
		for(i=0; i<datas.length; i++){
//			console.log(datas[i]);
			console.log(datas[i].firstName);
			var defaultprofilePic = "defaultpp_"+datas[i].firstName.substring(0,1).toUpperCase();
			$('.mynav-search-dropdown').prepend(`<a href='${datas[i].userName}' class='nav-link mynav-search-user'><img src='${datas[i].profilePicBase64}' onerror='this.onerror=null;this.src="\\/ID0420FF19OWidya\\/assets\\/${defaultprofilePic}.jpg"' class='circle'><span class='fullName'>${datas[i].firstName} ${datas[i].lastName}</span></a>`);
		}
		if(datas.length>5){
			$('.mynav-search-dropdown').append("<a href='search?inputValue="+inputValue+"' class='nav-link mynav-search-user'><span class='showmore'>show more..</span></a>")
		}
		
		
		
		
	}
})