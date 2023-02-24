<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

        <div class="container my-3 py-3 px-3">
            <div class="justify-content-center d-flex py-3">
                <h2>정보수정</h2>
            </div>
            <div class="justify-content-center d-flex mb-3">
                <div class="my-border-color-default px-3 py-3" style="width: 50%; ">
                    <form method="post" action="/company/update">
                        <div class="mb-3">
                            <label for="exampleInputCompanyName" class="form-label">회사명</label>
                            <input type="text" name="companyName" class="form-control" value="${coPrincipal.companyName}"
                                placeholder="Company Name">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputCompanyAddress" class="form-label">회사주소</label>
                            <input type="text" name="address" class="form-control" value="${coPrincipal.address}"
                                placeholder="Company Address">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputCompanyAddress" class="form-label">상세주소</label>
                            <input type="text" name="detailAddress" class="form-control" value="${coPrincipal.detailAddress}"
                                placeholder="Company Address">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">회사규모</label>
                            <select class="form-select"  name="companyScale">
                                <option value="대기업">대기업</option>
                                <option value="중견기업">중견기업</option>
                                <option value="중소기업">중소기업</option>
                                <option value="스타트업">스타트업</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">업종</label>
                            <select class="form-select" name="companyField">
                                <option value="웹개발">웹 개발</option>
                                <option value="게임개발">게임 개발</option>
                                <option value="SW개발">SW 개발</option>
                                <option value="앱개발">앱 개발</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputTelNumber" class="form-label">전화번호</label>
                            <input type="text" name="tel" class="form-control" value="${coPrincipal.tel}"
                                placeholder="하이픈(-) 없이 입력해주세요"
                                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword" class="form-label">비밀번호</label>
                            <input type="password" name="password" class="form-control" value="${coPrincipal.password}"
                                placeholder="Password">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputEmail" class="form-label">이메일</label>
                            <input type="email" name="email" class="form-control" value="${coPrincipal.email}" placeholder="Email">
                        </div>
                        <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary my-button-color-default">수정완료</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <%@ include file="../layout/footer.jsp" %>