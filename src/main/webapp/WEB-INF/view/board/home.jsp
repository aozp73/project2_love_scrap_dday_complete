<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3 py-3 px-3">
            <div class="row">
                <div id="mainImage1" class="col d-flex justify-content-center" onmouseenter="mouseEnterImages(this)"
                    onmouseleave="mouseLeaveImages(this)">
                    <img src="/images/newjeans.jpg" class="img-thumbnail" alt="Cinque Terre">
                </div>
                <div id="mainImage2" class="col d-flex justify-content-center" onmouseenter="mouseEnterImages(this)"
                    onmouseleave="mouseLeaveImages(this)">
                    <img src="/images/newjeans.jpg" class="img-thumbnail" alt="Cinque Terre">
                </div>
                <div id="mainImage3" class="col d-flex justify-content-center" onmouseenter="mouseEnterImages(this)"
                    onmouseleave="mouseLeaveImages(this)">
                    <img src="/images/newjeans.jpg" class="img-thumbnail" alt="Cinque Terre">
                </div>
            </div>
            <div>
                <!-- 카드 들어갈 곳 -->
                <div class="row">
                    <div class="col-md-3 py-2">
                        <div class="card col-lg-12">
                            <img class="card-img-top" style="height: 100px;" src="/images/newjeans.jpg"
                                alt="Card image">
                            <div class="card-body">
                                <div class="my-text-ellipsis">
                                    <h5>(주)엘지</h5>
                                </div>
                                <div class="my-text-ellipsis">
                                    2023년 IT직 경력/신입
                                </div>
                                <div class="my-text-ellipsis">
                                    채용
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between">
                                <div>(D-1)</div>
                                <div><i id="heart" class="fa-regular fa-heart my-xl my-cursor fa-lg"></i></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 py-2">
                        <div class="card col-lg-12">
                            <img class="card-img-top" style="height: 100px;" src="/images/newjeans.jpg"
                                alt="Card image">
                            <div class="card-body">
                                <div class="my-text-ellipsis">
                                    <h5>(주)엘지</h5>
                                </div>
                                <div class="my-text-ellipsis">
                                    2023년 IT직 경력/신입
                                </div>
                                <div class="my-text-ellipsis">
                                    채용
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between">
                                <div>(D-1)</div>
                                <div><i id="heart" class="fa-regular fa-heart my-xl my-cursor fa-lg"></i></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 py-2">
                        <div class="card col-lg-12">
                            <img class="card-img-top" style="height: 100px;" src="/images/newjeans.jpg"
                                alt="Card image">
                            <div class="card-body">
                                <div class="my-text-ellipsis">
                                    <h5>(주)엘지</h5>
                                </div>
                                <div class="my-text-ellipsis">
                                    2023년 IT직 경력/신입
                                </div>
                                <div class="my-text-ellipsis">
                                    채용
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between">
                                <div>(D-1)</div>
                                <div><i id="heart" class="fa-regular fa-heart my-xl my-cursor fa-lg"></i></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 py-2">
                        <div class="card col-lg-12">
                            <img class="card-img-top" style="height: 100px;" src="/images/newjeans.jpg"
                                alt="Card image">
                            <div class="card-body">
                                <div class="my-text-ellipsis">
                                    <h5>(주)엘지</h5>
                                </div>
                                <div class="my-text-ellipsis">
                                    2023년 IT직 경력/신입
                                </div>
                                <div class="my-text-ellipsis">
                                    채용
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between">
                                <div>(D-1)</div>
                                <div><i id="heart" class="fa-regular fa-heart my-xl my-cursor fa-lg"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function mouseEnterImages(e) {
                console.log(e.getAttribute('id'));
                let id = e.getAttribute('id');
                $("#" + id).removeClass("col");
                $("#" + id).addClass("col-6");
            }

            function mouseLeaveImages(e) {
                console.log(e.getAttribute('id'));
                let id = e.getAttribute('id');
                $("#" + id).removeClass("col-6");
                $("#" + id).addClass("col");
            }
        </script>

        <%@ include file="../layout/footer.jsp" %>