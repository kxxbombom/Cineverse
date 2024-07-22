$(document).ready(function() {
/*    let currentPage = 1;
    const rowCount = 20;*/
/*    const displayedMovies = new Set();*/
	const selectedGenres = new Set();
/*	 let status = $("#statusCheckbox").is(':checked') ? 1 : '';*/
    // 상영 중인 영화 필터링
    $("#statusCheckbox").change(function() {
       var status = $(this).is(':checked') ? 1 : '';
        location.href = '/movie/movieList?status=' + status;
        
/*        currentPage = 1;
        loadMovies(true);*/
    });

   // 최신순, 북마크순 체크박스 상태 관리
    $("input[name='movieorder']").change(function() {
        var selectedCheckboxes = $("input[name='movieorder']:checked");

        if (selectedCheckboxes.length > 1) {
            // 이미 체크된 다른 체크박스를 해제
            $("input[name='movieorder']").not(this).prop('checked', false);
         
        }

        // 기본 정렬 값 설정 
        var movieorder = $("input[name='movieorder']:checked").val() || 1;
        console.log('Selected movieorder:', movieorder);  // 디버그 로그 추가

        location.href = '/movie/movieList?keyfield=' + $('#keyfield-select').val() + '&keyword=' + $('#keyword').val() + '&movieorder=' + movieorder;
    });

    // 체크박스 해제 방지
    $("input[name='movieorder']").click(function() {
        if ($("input[name='movieorder']:checked").length === 0) {
            $(this).prop('checked', true);
        }
    });

    // 기본 정렬 값을 최신순으로 설정
    if ($("input[name='movieorder']:checked").length === 0) {
        $("input[name='movieorder'][value='1']").prop('checked', true);
    }

  /*  // 더보기 버튼 클릭 이벤트
    $("#loadMoreButton").click(function() {
        loadMoreMovies();
    });
    
    function loadMovies(reset = false) {
        if (reset) {
            currentPage = 1;
            displayedMovies.clear();
            $("#movieListContainer ul.movie-list").empty();
        }
        loadMoreMovies();
    }

    function loadMoreMovies() {
        currentPage++;
        $.ajax({
            url: "/movie/loadMoreMovies",
            method: "GET",
            data: {
                pageNum: currentPage,
                rowCount: rowCount,
                keyfield: $('#keyfield').val(),
                keyword: $('#keyword').val(),
                movieorder: $("input[name='movieorder']:checked").val() || 1,
            },
            success: function(response) {
                const movielist = response.movielist;

                if (movielist.length > 0) {
                    let html = '';
                    movielist.forEach(movie => {
                        if (!displayedMovies.has(movie.m_code)) {
                            displayedMovies.add(movie.m_code);
                            html += `
                                <li class='movie'>
                                    <img alt='영화1' src='/upload/${movie.m_filename}' onclick="location.href='movieDetail?m_code=${movie.m_code}'">
                                    <p><a href='movieDetail?m_code=${movie.m_code}'><b>${movie.m_name}</b></a></p>
                                    <div class='bookAopen'>
                                        <div>예매율 34.1% | 개봉일 ${movie.m_opendate}</div>
                                    </div>
                                    <div class='movie-button'>
                                        <div class='movie-fav-button-detail'>
                                            <img class='output_bookMark' data-num='${movie.m_code}' src='/images/heart01.png'>
                                            <span class='output_mfcount'></span>
                                        </div>
                                        <div class='movie-reservation-button-list'>예매하기</div>
                                    </div>
                                </li>`;
                        }
                    });
                    $("#movieListContainer ul.movie-list").append(html);
                }

                if (movielist.length < rowCount) {
                    $("#loadMoreButton").hide();
                }
            },
            error: function(xhr, status, error) {
                console.error("Error loading more movies:", error);
            }
        });
    }*/
 
   // 장르 체크박스 클릭 이벤트
    $('.genre-checkbox').change(function() {
        let genre = $(this).val();
        if ($(this).is(':checked')) {
            selectedGenres.add(genre);
        } else {
            selectedGenres.delete(genre);
        }
        filterMoviesByGenres();
    });

    // 장르로 영화 필터링
    function filterMoviesByGenres() {
        $.ajax({
            url: '/movie/filterMoviesByGenres',
            type: 'GET',
            traditional: true,  // 이 옵션을 사용하여 배열 형태의 데이터를 전송
            data: { genres: Array.from(selectedGenres) },
            dataType: 'json',
            success: function(response) {
                $("#movieListContainer ul.movie-list").empty();
                response.forEach(function(movie) {
                    let html = `
                        <li class='movie'>
                            <img alt='영화1' src='/upload/${movie.m_filename}' onclick="location.href='movieDetail?m_code=${movie.m_code}'">
                            <p><a href='movieDetail?m_code=${movie.m_code}'><b>${movie.m_name}</b></a></p>
                            <div class='bookAopen'>
                                <div>예매율 34.1% | 개봉일 ${movie.m_opendate}</div>
                            </div>
                            <div class='movie-button'>
                                <div class='movie-fav-button-detail'>
                                    <img class='output_bookMark' data-num='${movie.m_code}' src='/images/heart01.png'>
                                    <span class='output_mfcount'></span>
                                </div>
                                <div class='movie-reservation-button-list'>예매하기</div>
                            </div>
                        </li>`;
                    $("#movieListContainer ul.movie-list").append(html);
                });
            },
            error: function(xhr, status, error) {
                console.error('Error filtering movies:', error);
            }
        });
    }
/*      // 초기 로드
    loadMovies();*/
});