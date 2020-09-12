var likeManager = (function () {
    var add = function (obj) {
        console.log(obj);
        $.ajax({
            type: 'post',
            url: '/like/' + obj.memberId +'/'+obj.boardId,
            data: JSON.stringify(obj),
            contentType: "application/json",
            dataType: 'json',
            success: function (data){
                alert(data.msg);
            }
        });

    };

    return {
        add: add,
    }

})();
