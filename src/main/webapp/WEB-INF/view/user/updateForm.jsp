<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
    
    <div class="container my-3 py-3 px-3">
        <div class="justify-content-center d-flex py-3">
            <h2>회원 정보 수정</h2>
        </div>
        <div class="justify-content-center d-flex">
            <div class="my-border-color-default px-3 py-3" style="width: 50%; ">
                <form action="/user/update" method="post">
                    <div class="mb-3">
                        <label class="form-label">변경 비밀번호</label>
                        <input name="password" type="password" class="form-control" placeholder="Password">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">비밀번호 확인</label>
                        <input type="password" class="form-control" placeholder="Confirm Password">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">이메일</label>
                        <input name="email" type="email" class="form-control" placeholder="Email" value="${user.email}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">주소</label>
                        <input name="address" type="text" class="form-control" placeholder="Address" value="${user.address}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">상세주소</label>
                        <input name="detailAddress" type="text" class="form-control" placeholder="Detail Address" value="${user.detailAddress}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">연락처</label>
                        <input name="tel" type="text" class="form-control" placeholder="Tel" value="${user.tel}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">경력 연차</label>
                        <input name="career" type="number" class="form-control" placeholder="Career Year" min="0" value="${user.career}">
                    </div>
                    <label class="form-label">관심분야</label>
                    <div class="mb-5" style="display: grid;
                                        grid-template-columns: 1fr 8fr 2fr;">
                        <div></div>
                        <div>
                            <div class="mb-2"></div>
                            <div class="mb-3">
                            </div>
                            <div class="mb-2">
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxJava" value="option1">
                                    <label class="form-check-label" for="inlineCheckbox1">Java</label>
                                </div>
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxSpring" value="option2">
                                    <label class="form-check-label" for="inlineCheckbox2">Spring</label>
                                </div>
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxOracle" value="option1">
                                    <label class="form-check-label" for="inlineCheckbox1">Oracle</label>
                                </div>
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxMySQL" value="option2">
                                    <label class="form-check-label" for="inlineCheckbox2">MySQL</label>
                                </div>
                            </div>
                            <div>
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxReact" value="option1">
                                    <label class="form-check-label" for="inlineCheckbox1">React</label>
                                </div>
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxPython" value="option2">
                                    <label class="form-check-label" for="inlineCheckbox2">Python</label>
                                </div>
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxC" value="option1">
                                    <label class="form-check-label" for="inlineCheckbox1">C#</label>
                                </div>
                                <div class="form-check form-check-inline" style="width: 19%">
                                    <input class="form-check-input" type="checkbox" id="CheckboxSwift" value="option2">
                                    <label class="form-check-label" for="inlineCheckbox2">Swift</label>
                                </div>
                            </div>
                            <div>

                            </div>
                        </div>
                        <div></div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary my-button-color-default">정보 수정</button>
                        <a href="/user/${user.id}/resumeForm">
                            <div class="btn btn-secondary ms-3">이력서 수정</div>
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
 <%@ include file="../layout/footer.jsp" %>