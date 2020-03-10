var main = {
    init: function () {
        var _this = this;
        $('#coupon-save').on('click', function () {
            _this.save();
        });
        $('#coupon-update').on('click', function () {
            _this.update();
        });
        $('#coupon-delete').on('click', function () {
            _this.delete();
        });
    },
    save: function () {
        var data = {
            name: $('#name').val(),
            month: $('#month').val(),
            rate: $('#rate').val(),
            expirationDate: $('#expirationDate').val()
        };
        $.ajax({

            type: 'POST',
            url: '/api/v2/coupon',
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',

            data: JSON.stringify(data)
        }).done(function (message) {
            alert(message);
            window.location.href = '/';
        }).fail(function (error) {
            console.log(error);
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        var data = {
            name: $('#name').val(),
            month: $('#month').val(),
            rate: $('#rate').val(),
            expirationDate: $('#expirationDate').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v2/coupon/'+id,
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (message) {
            alert(message);
            window.location.href = '/';
        }).fail(function (error) {
            console.log(error);
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v2/coupon/'+id,
            dataType: 'text',
            contentType: 'application/json; charset=utf-8',
        }).done(function (message) {
            alert(message);
            window.location.href = '/';
        }).fail(function (error) {
            console.log(error);
            alert(JSON.stringify(error));
        });
    }
};
main.init();