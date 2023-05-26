class Cart {
  String? cartId;
  String? courseId;
  String? courseName;
  String? coursePrice;
  String? cartQty;
  String? priceTotal;

  Cart(
      {this.cartId,
      this.courseId,
      this.courseName,
      this.coursePrice,
      this.cartQty,
      this.priceTotal});

  Cart.fromJson(Map<String, dynamic> json) {
    cartId = json['cart_id'];
    courseId = json['subject_id'];
    courseName = json['subject_name'];
    coursePrice = json['subject_price'];
    cartQty = json['cart_qty'];
    priceTotal = json['price_total'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['cartid'] = cartId;
    data['subject_id'] = courseId;
    data['subject_name'] = courseName;
    data['subject_price'] = coursePrice;
    data['cart_qty'] = cartQty;
    data['price_total'] = priceTotal;
    return data;
  }
}