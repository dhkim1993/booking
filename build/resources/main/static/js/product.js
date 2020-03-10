var main = {
    init: function () {
        var _this = this;
        $('#product-save').on('click', function () {
            _this.save();
        });
        $('#product-update').on('click', function () {
            _this.update();
        });
        $('#product-delete').on('click', function () {
            _this.delete();
        });
    },
    save: function () {
        var data = {
            categoryId: $('#categoryId').val(),
            name: $('#name').val(),
            description: $('#description').val(),
            count: $('#count').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            location: $('#location').val(),

            option1: $('#option1').val(),
            option2: $('#option2').val(),
            option3: $('#option3').val(),
            price1: $('#price1').val(),
            price2: $('#price2').val(),
            price3: $('#price3').val(),

            firstTime: $('#firstTime').val(),
            secondTime: $('#secondTime').val(),
            thirdTime: $('#thirdTime').val(),
            fourthTime: $('#fourthTime').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v2/product',
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
            description: $('#description').val(),
            count: $('#count').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            location: $('#location').val(),
            option1: $('#option1').val(),
            option2: $('#option2').val(),
            option3: $('#option3').val(),
            price1: $('#price1').val(),
            price2: $('#price2').val(),
            price3: $('#price3').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v2/product/'+id,
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
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v2/product/'+id,
            dataType: 'text',
            contentType:'application/json; charset=utf-8'
        }).done(function(message) {
            alert(message);
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};
main.init();