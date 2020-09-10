var replyManager = (function () {
  var getAll = function (obj, callback) {
    $.ajax({
      type: 'get',
      url: '/replies/' + obj,
      dataType: 'json',
      success: callback
    });
    console.log("obj: "+obj);
    console.log("callback :" +callback);


  };

  var add = function (obj, callback) {

    console.log(obj);
    $.ajax({
      type: 'post',
      url: '/replies/' + obj.boardId,
      data: JSON.stringify(obj),
      contentType: "application/json",
      dataType: 'json',
      success: callback
    });

  };

  var update = function (obj, callback) {
    $.ajax({
      type: 'put',
      url: '/replies/' + obj.bno + "/" + obj.rno,
      data: JSON.stringify(obj),
      contentType: "application/json",
      dataType: 'json',
      success: callback
    });
  };

  var remove = function (obj, callback) {

    $.ajax({
      type: 'delete',
      url: '/replies/' + obj.bno + "/" + obj.rno,
      dataType: 'json',
      success: callback
    });

  };

  return {
    getAll: getAll,
    add: add,
    update: update,
    remove: remove
  }

})();
