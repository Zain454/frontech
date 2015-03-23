$(function() {
    var $loginForm = $('#login-form');

    $loginForm.validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: "Please enter your username"
            },
            password: {
                required: "Please enter your password"
            }
        }
    });

    $loginForm.submit(function () {
        if (!$loginForm.valid()) {
            return false;
        }

        var username = $loginForm.find('#username').val();
        var password = $loginForm.find('#password').val();

        $.ajax({
            type: "POST",
            url: '/login',
            data: {'username' : username, 'password' : password},
            success: function(data) {
                if (data.status == 'success') {
                    $('.login').transition('fade');
                    $('.casino').transition('fade');

                    loadPlayer(data.player, username);
                    loadGames();
                    loadCategories();
                } else {
                    $('#response').removeClass('hide')
                    $('#login-form .alert').html(data.error);
                }
            }
        });

        return false;
    });

    $('.logout').click(function () {
        $.ajax({
            type: "POST",
            url: '/logout',
            data: {'username' : $('.player').data('username')},
            success: function(data) {
                if (data.status == 'success') {
                    $('#login-form')[0].reset();

                    $('.casino').transition('fade');
                    $('.login').transition('fade');
                } else {
                    alert(data.error);
                }
            }
        });

        return false;
    });

    $('#back').click(function () {
        $('.casino').transition('fade');
        $('.ingame').transition('fade');
    });

    function loadPlayer(player, username) {
        player.username = username;

        $("#player-container").loadTemplate($("#player-template"), player);
    };

    function loadGames() {
        $.ajax({
            type: "POST",
            url: '/games',
            success: function(data) {
                $('.ui.search')
                    .search({
                        source : data,
                        searchFields : ['name', 'description'],
                        searchFullText: true,
                        minCharacters: 0,
                        cache: false,
                        onResults: function (data) {
                            $('.game.item').hide();

                            for (var i = 0; i < data.results.length; i++) {
                                $('#' + data.results[i].code).show();
                            }
                        }
                    })
                ;

                $("#games-container").loadTemplate($("#games-template"), data);

                $('.play').on('click', function () {
                    $('.casino').transition('fade');
                    $('.ingame').transition('fade');
                    comeon.game.launch($(this).id);
                });
            }
        });
    };

    function loadCategories() {
        $.ajax({
            type: "POST",
            url: '/categories',
            success: function(data) {
                $("#categories-container").loadTemplate($("#categories-template"), data);

                $('.category.item').on('click', function () {
                    var $id = $(this).data('id') + '';

                    $('.game.item').hide();

                    $('.game.item').filter(function (index) {
                        return $.inArray($id, $(this).data('category-ids').split(',')) != -1;
                    }).show();
                });
            }
        });
    };

});