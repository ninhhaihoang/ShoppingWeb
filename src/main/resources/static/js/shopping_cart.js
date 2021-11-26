$(document).ready(function () {
    updateTotal();

    $(".minusButton").on("click", function(evt) {
        evt.preventDefault();
        decreaseQuantity($(this));
    });


    $(".plusButton").on("click", function(evt) {
        evt.preventDefault();
        increseQuantity($(this));
    });
});

function decreaseQuantity(link) {
        productId = $(this).attr("pid");
        qtyInput = $("#quantity" + productId);

        newQty = parseInt(qtyInput.val()) - 1;
        if (newQty > 0) {
            qtyInput.val(newQty);
            updateQuantity(productId, newQty);
        }
}

function increseQuantity(link) {
    productId = $(this).attr("pid");
    qtyInput = $("#quantity" + productId);

    newQty = parseInt(qtyInput.val()) + 1;
    if (newQty < 10) {
        qtyInput.val(newQty);
        updateQuantity(productId, newQty);
    }
}

function updateQuantity(productId, quantity) {
    let url = contextPath + "cart/update/" + productId + "/" + quantity;

    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(crsfHeaderName, csrfValue);
        }
    }).done(function (newSubtotal) {
        updateSubtotal();
        updateTotal();
    }).fail(function () {
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text("Error while adding product to shopping cart.");
        $("#myModal").modal();
    });
}

function updateSubtotal(){

}

function updateTotal() {
    total = 0;

    $(".productSubtotal").each(function (index, element) {
        total = total + parseInt(element.innerHTML);
    });

    $("#totalAmount").text(total + " VND");
}

