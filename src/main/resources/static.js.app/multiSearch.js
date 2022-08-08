var main = {

    init : function() {
        var _this = this;
        $('#btn_srch').on('click', function() {

            _this.search();
        });

    },

    search : function() {

        var webview_g = $('#ifr_g');
        var webview_n = $('#ifr_n');

        var query = $('#inp_query').val();

        webview_g.attributes('src', "https://www.google.co.kr/search?q="+query);
        webview_n.attributes('src', "https://search.naver.com/search.naver?query="+query);

    }

};

main.init();