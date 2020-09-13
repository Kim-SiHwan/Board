var replyManager = (function () {
  var getAll = function (obj, callback) {
    $.ajax({
      type: 'get',
      url: '/replies/' + obj,
      dataType: 'json',
      success: callback
    });
  };

  var add = function (obj, callback) {
    $.ajax({
      type: 'post',
      url: '/replies/' + obj.boardId,
      data: JSON.stringify(obj),
      contentType: "application/json",
      dataType: 'json',
      success: callback

    });

  };

  var remove = function (obj, callback) {

    $.ajax({
      type: 'delete',
      url: '/replies/' + obj.boardId +'/'+obj.replyId,
      dataType: 'json',
      success: callback
    });

  };

  return {
    getAll: getAll,
    add: add,
    remove: remove
  }

})();
