var main = {

    init : function() {
        var _this = this;
        $('#btn_srch').on('click', function() {

            _this.search();
        });

    },

    search : function() {

        // var webview_g = $('#ifr_g');
        // var webview_n = $('#ifr_n');

        var query = $('#input_query').val();

        // webview_g.attr('src', "https://www.google.co.kr/search?q="+query);
        // webview_n.attr('src', "https://search.naver.com/search.naver?query="+query);



    }



};

$('#input_query').on('click', function(event) {

    let key = event.key;

    // if (event.which == 13) 대신에 아래와 같이 쓰자
    // Enter는 눌리는데 클릭은 안되네??
    if(key === 'Enter' || key === 13) {
        document.querySelector('#btn_srch').click();
        event.preventDefault();
    }

});

main.init();

