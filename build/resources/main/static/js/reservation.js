$(function () {
    var option1Name= '{{option1.optionName}}';
    var option2Name= '{{option2.optionName}}';
    var option3Name= '{{option3.optionName}}';
    var price1 = '{{option1.price}}';
    var price2 = '{{option2.price}}';
    var price3 = '{{option3.price}}';
    var count1 =0;
    var count2 =0;
    var count3 =0;
    var totalPrice = 0;
    $('#option1-minus').click(function(){
        --count1;
        if (count1 >= 0) {
            totalPrice -= parseInt(price1);
            $("#choice-option1-count").html(count1);
        }
        if (count1 > 0) {
            $("#choice-option1-name").html(option1Name);
        }
        $("#current-total-price").html(totalPrice);
        $("#current-option1-count").html(count1);
    });
    $('#option1-plus').click(function(){
        ++count1;
        totalPrice += parseInt(price1);
        $("#choice-option1-name").html(option1Name);
        $("#choice-option1-count").html(count1);
        $("#current-total-price").html(totalPrice);
        $("#current-option1-count").html(count1);
    });
    $('#option2-minus').click(function(){
        --count2;
        if (count2 >= 0) {
            totalPrice -= parseInt(price2);
            $("#choice-option2-count").html(count2);
        }
        if (count2 > 0) {
            $("#choice-option2-name").html(option2Name);
        }
        $("#current-total-price").html(totalPrice);
        $("#current-option2-count").html(count2);
    });
    $('#option2-plus').click(function(){
        ++count2;
        totalPrice += parseInt(price2);
        $("#choice-option2-name").html(option2Name);
        $("#choice-option2-count").html(count2);
        $("#current-total-price").html(totalPrice);
        $("#current-option2-count").html(count2);
    });$('#option3-minus').click(function(){
        --count3;
        if (count3 >= 0) {
            totalPrice -= parseInt(price3);
            $("#choice-option3-count").html(count3);
        }
        if (count3 > 0) {
            $("#choice-option3-name").html(option3Name);
        }
        $("#current-total-price").html(totalPrice);
        $("#current-option3-count").html(count3);
    });
    $('#option3-plus').click(function(){
        ++count3;
        totalPrice += parseInt(price3);
        $("#choice-option3-name").html(option3Name);
        $("#choice-option3-count").html(count3);
        $("#current-total-price").html(totalPrice);
        $("#current-option3-count").html(count3);
    });
});