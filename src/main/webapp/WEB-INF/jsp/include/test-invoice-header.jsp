<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<thead>
    <tr>
        <td colspan="5" style="border-top: none;">
        <%@include file="header.jsp" %>
        <!--End InvoiceTop-->
        <div id="invoice-mid">
            <div class="row">
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">Name</hp>
                </div>
                <div class="col-sm-2">
                        <hp>${patient.namePrefix} ${patient.firstName} ${patient.lastName}</hp>
                </div>
                <div class="col-sm-1">
                        <hp style="font-weight: 700;">Lab Id</hp>
                </div>
                <div class="col-sm-2">
                        <hp style="text-transform: capitalize;">${facility.facilityCode}</hp>
                </div>
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">Date</hp>
                </div>
                <div class="col-sm-3">
                        <hp>${invoice.registerDate}</hp>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">Patient Id</hp>
                </div>
                <div class="col-sm-2">
                        <hp>${patient.patientIdPrefix}${patient.patientId}</hp>
                </div>
                <div class="col-sm-1">
                        <hp style="font-weight: 700;">Doctor</hp>
                </div>
                <div class="col-sm-2">
                    <hp style="text-transform: capitalize;"><c:if test="${doctor !=null}">${doctor.name}</c:if><c:if test="${doctor ==null}">NA</c:if></hp>
                </div>
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">Invoice No.</hp>
                </div>
                <div class="col-sm-3">
                        <hp>${invoiceNo}</hp>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">Age & Sex</hp>
                </div>
                <div class="col-sm-2">
                        <hp style="text-transform: capitalize;">${patient.age} /${patient.gender}</hp>
                </div>
                <div class="col-sm-1">
                        <hp style="font-weight: 700;">GST</hp>
                </div>
                <div class="col-sm-2">
                    <hp><c:if test="${format.tax == 0}">NA</c:if><c:if test="${format.tax != 0}">${format.tax} %</c:if></hp>
                </div>
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">SAC Code</hp>
                </div>
                <div class="col-sm-3">
                    <hp><c:if test="${format.sac == null || format.sac == ''}">NA</c:if><c:if test="${format.sac != null && format.sac != ''}">${format.sac}</c:if></hp>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">Date of birth</hp>
                </div>
                <div class="col-sm-2">
                        <hp>${patient.dateOfBirth}</hp>
                </div>
                <div class="col-sm-1">
                        <hp style="font-weight: 700;">Payment</hp>
                </div>
                <div class="col-sm-2">
                        <hp>Cash</hp>
                </div>
                <div class="col-sm-2">
                        <hp style="font-weight: 700;">CIN</hp>
                </div>
                <div class="col-sm-3">
                    <hp><c:if test="${format.cin == null || format.cin == ''}">NA</c:if><c:if test="${format.cin != null && format.cin != ''}">${format.cin}</c:if></hp>
                </div>
            </div>    
        </div>
        <!--End Invoice Mid-->
        </td>
    </tr>
</thead>