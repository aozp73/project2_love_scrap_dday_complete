<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

    <div class="container my-3">
        <div class="mt-3">
            <div class="row">
                <div class=" col-sm-4 p-3">
                    <div class="my-border-color-default m-3 rounded-1" style="height: 100%;">
                        <div class="col-12 p-3">
                            <div class="row">
                                <div class="col-sm-5 m-3">
                                    <h3>김실명</h3>
                                </div>
                                <div class="col-sm-5">
                                    <img src="/images/newjeans.jpg" class="rounded-circle"
                                        style="max-width: 100%; height: auto;">
                                </div>
                            </div>
                        </div>
                        <div class="col-10 m-3 p-3">
                            <hr>
                            <h3>빙그레 공채 1차</h3>
                            <!-- !현재 신청중인 공고가 없다면 안보임 -->
                            <hr>
                        </div>
                        <div class="col-10 m-3 p-3">
                            <h3 class="mt-3">주요 인적사항</h3>
                            <hr>
                            <ul class="nav nav-pills flex-column">
                                <li class="nav-item">
                                    <div class="row">
                                        <div class="col-3 ps-3 pe-0">
                                            주소 :
                                        </div>
                                        <div class="col-9 ps-0">
                                            부산광역시 부산진구 <br>
                                            xxx동 yyy구 zzz번지
                                        </div>
                                    </div>
                                </li>
                                <br>
                                <li class="nav-item">
                                    <div class="row">
                                        <div class="col-3 ps-3 pe-0">
                                            학력 :
                                        </div>
                                        <div class="col-9 ps-0">
                                            4년제 대학 졸업
                                        </div>
                                    </div>
                                </li>
                                <li class="nav-item">
                                    <div class="row">
                                        <div class="col-3 ps-3 pe-0">
                                            경력 :
                                        </div>
                                        <div class="col-9 ps-0">
                                            n년차
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="col-10 m-3 p-3">
                            <h3 class="mt-3">연락처</h3>
                            <hr>
                            <ul class="nav nav-pills flex-column">
                                <li class="nav-item">
                                    <div class="row">
                                        <div class="col-4 px-0" style="text-align: right;">
                                            전화번호 :
                                        </div>
                                        <div class="col-8">
                                            01012345678
                                        </div>
                                    </div>
                                </li>
                                <li class="nav-item">
                                    <div class="row">
                                        <div class="col-4 px-0" style="text-align: right;">
                                            email :
                                        </div>
                                        <div class="col-8">
                                            이메일@이메일.이메일
                                            <!-- 이메일이 길어질경우 후에 간격 수정가능 -->
                                        </div>
                                    </div>
                                </li>

                            </ul>
                        </div>

                        <hr class="d-sm-none">
                    </div>
                </div>
                <div class="col-sm-8 p-3 ">
                    <div class="p-3 m-3 my-border-color-default rounded-1">
                        <h2 class="p-3"><b>지원자 자기 소개</b></h2>
                        <hr>
                        <h5>자신감이 넘치는 사람</h5>

                        <p>저는 몇남몇녀의 몇남으로 태어나 .... mollit anim id est laborum consectetur adipiscing
                            elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                            veniam, quis nostrud exercitation ullamco. Sunt in culpa qui officia deserunt mollit
                            anim
                            id est laborum consectetur adipiscing elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                            veniam, quis nostrud exercitation ullamco.</p>
                        <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing
                            elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                            veniam, quis nostrud exercitation ullamco. Sunt in culpa qui officia deserunt mollit
                            anim
                            id est laborum consectetur adipiscing elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                            veniam, quis nostrud exercitation ullamco.</p>
                    </div>
                    <div class="p-3 m-3 my-border-color-default rounded-1">
                        <h2 class="p-3"><b>경력 사항</b></h2>
                        <hr>
                        <h5>핵심 경력</h5>
                        <ul class="nav nav-pills flex-column">
                            <li class="nav-item">
                                보유 기술1
                            </li>
                            <li class="nav-item">
                                보유 기술2
                            </li>
                        </ul>
                    </div>
                    <div class="row p-3">
                        <div class="col-sm-6 b-3 p-3">
                        </div>
                        <div class="col-sm-2 b-3 p-3">
                        </div>
                        <div class="col-sm-2 ">
                            <button type="button" class="col-12 btn btn-outline-info">채용</button>
                        </div>
                        <div class=" col-sm-2 ">
                            <button type="button" class="col-12 btn btn-outline-danger">비채용</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
 <%@ include file="../layout/footer.jsp" %>