/**
 * 
 */

function getCountry(callback){
	$.ajax({
		type : "POST",
		url : "getCountry.action",
		async: false,
		success : function(datas) {
			callback(datas)
		}
	})
}

function getCity(countryId, callback){
	$.ajax({
		type : "POST",
		data : {
			countryID: countryId
		},
		url : "getCity.action",
		success : function(datas) {
			callback(datas)
		}
	})
}

function getJobList(callback){
	$.ajax({
		type : "POST",
		data : JSON.stringify(getFilterData()),
		async: false,
		contentType : 'application/json',
		url : "getJobList.action",
		success : function(datas) {
//			alert("success")
			if(datas.length >0){
				console.log("jobdatas:",datas);
				callback(datas)
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

		},
		error: function(data){
			alert("error")
		}
	})
}
