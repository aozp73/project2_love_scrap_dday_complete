<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>

    <div class="container my-3 py-3 px-3">
        <div class="justify-content-center d-flex py-3">
            <h2>정보수정</h2>
        </div>
        <div class="justify-content-center d-flex mb-3">
            <div class="my-border-color-default px-3 py-3" style="width: 50%; ">
                <form>
                    <div class="mb-3">
                        <label for="exampleInputCompanyName" class="form-label">회사명</label>
                        <input type="text" class="form-control" placeholder="Company Name">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputCompanyAddress" class="form-label">회사주소</label>
                        <input type="text" class="form-control" placeholder="Company Address">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputCompanyScale" class="form-label">회사규모</label>
                        <input type="text" class="form-control" placeholder="Company Scale">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputCompnayField" class="form-label">업종</label>
                        <input type="text" class="form-control" placeholder="Compnay Field">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputTelNumber" class="form-label">전화번호</label>
                        <input type="text" class="form-control" placeholder="하이픈(-) 없이 입력해주세요"
                            oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" placeholder="Password">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail" class="form-label">이메일</label>
                        <input type="email" class="form-control" placeholder="Email">
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary my-button-color-default">수정완료</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
 <%@ include file="../layout/footer.jsp" %>