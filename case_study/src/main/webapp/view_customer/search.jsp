<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="jquery-3.6.0.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <style>
        body {
            background-image: url("/new_folder/BG2.jpg");
            background-position: right bottom;
            background-repeat: no-repeat;
            background-size: contain;
            background-attachment: fixed;
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="col-12" style="padding: 0">
                <img src="https://www.365travel.asia/images/tour/items/img2/furama-resort-danang-banner.jpg" width=100% height="200px">
            </div>
        </div>
        <div class="row bg-white">
            <div class="col-3"></div>
            <div class="col-6"><nav class="navbar navbar-expand-lg navbar-light bg-white">
                <a class="navbar-brand" href="/furama"><img src="new_folder/FURAMA.png" width="40px"/></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active mr-2">
                            <a class="nav-link" href="#">Employee <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active mr-2">
                            <a class="nav-link" href="/customer">Customer <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active mr-2">
                            <a class="nav-link" href="#">Serivce <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Contract <span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav></div>
            <div class="col-3"></div>
        </div>
    </div>
</header>
<div class="container">
    <div class="row">
        <div class="col-6 mt-3">
            <a class="btn btn-secondary" href="/customer?action=create" role="button">Create new customer</a>
        </div>
        <div class="col-2"></div>
        <div class="col-4 mt-3 bg-white">
            <form action="/customer?action=search" method="post">
                <fieldset>
                    <legend>Search</legend>
                    <table>
                        <tr>
                            <td><label for="searchCode">Search by customer'code</label></td>
                            <td><input type="text" id="searchCode" name="code" placeholder="Input a code"></td>
                        </tr>
                        <tr>
                            <td><label for="searchName">Search by customer'name : </label></td>
                            <td><input type="text" id="searchName" name="name" placeholder="Input a name"></td>
                        </tr>
                        <tr>
                            <td><label for="searchAddress">Search by customer'address : </label></td>
                            <td><input type="text" id="searchAddress" name="address" placeholder="Input a address"></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: right"><input type="submit" role="button" value="search"></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-12 mt-2">
            <a class="btn btn-secondary" href="/furama" role="button">Back to home page</a>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col-lg-12">
            <table id="tableCustomer" class="table table-striped table-bordered" st
                   style="background-color: white ;width:100%; font-size: small">
                <thead>
                <tr>
                    <th>#</th>
                    <th hidden>Id</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Date of birth</th>
                    <th>Gender</th>
                    <th>Id Card</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Type of customer</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cusList" items="${cusList}" varStatus="loop">
                    <tr>
                        <td><c:out value="${loop.count}"/></td>
                        <td hidden><c:out value="${cusList.id}"/></td>
                        <td><c:out value="${cusList.customerCode}"/></td>
                        <td><c:out value="${cusList.name}"/></td>
                        <td><c:out value="${cusList.birthday}"/></td>
                        <td>
                            <c:if test="${cusList.gender == true}">
                                Male
                            </c:if>
                            <c:if test="${cusList.gender == false}">
                                Female
                            </c:if>
                        </td>
                        <td><c:out value="${cusList.idCard}"/></td>
                        <td><c:out value="${cusList.phone}"/></td>
                        <td><c:out value="${cusList.email}"/></td>
                        <td><c:out value="${cusList.address}"/></td>
                        <td><c:out value="${cusList.typeName}"/></td>
                        <td><a class="btn btn-warning" href="/customer?action=update&id=${cusList.id}" role="button">update</a></td>
                        <td><a class="btn btn-danger" href="/customer?action=delete&id=${cusList.id}&name=${cusList.name}" role="button"
                               data-toggle="modal" data-target="#exampleModal" onclick="setIdDeleteToForm('${cusList.id}','${cusList.name}')">delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Confirm xoá</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <span>Are you sure to delete </span>
                <input style="border: none;color: red" type="text" name="name" id="nameDelete" readonly>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="submitFormDelete()">Save changes</button>
            </div>
        </div>
    </div>
</div>

<form action="/customer" id="formDelete">
    <input type="hidden" name="action" value="delete" />
    <input  type="hidden" name="id" id="idCustomer" />
</form>

<script src="/jquery/jquery-3.5.1.min.js"></script>
<script src="/datatables/js/jquery.dataTables.min.js"></script>
<script src="/datatables/js/dataTables.bootstrap4.min.js"></script>
<script>
    $(document).ready(function() {
        $('#tableCustomer').dataTable( {
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 4
        } );
    } );

    function setIdDeleteToForm(idCustomer,nameDelete) {
        document.getElementById("idCustomer").value = idCustomer;
        document.getElementById("nameDelete").value = nameDelete;
    }

    function submitFormDelete() {
        document.getElementById("formDelete").submit();
    }

    <%--if(${msg != null}){document.getElementById("abc").click();}--%>
</script>
</body>
</html>
