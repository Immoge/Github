class Tutor {
  String? tutorId;
  String? tutorEmail;
  String? tutorPhone;
  String? tutorName;
  String? tutorDesc;
  String? tutorDatereg;
  String? tutorCourse;

  Tutor(
      {this.tutorId,
      this.tutorEmail,
      this.tutorPhone,
      this.tutorName,
      this.tutorDesc,
      this.tutorDatereg,
      this.tutorCourse
}
);

  Tutor.fromJson(Map<String, dynamic> json) {
    tutorId = json['tutor_id'];
    tutorEmail = json['tutor_email'];
    tutorPhone = json['tutor_phone'];
    tutorName = json['tutor_name'];
    tutorDesc = json['tutor_description'];
    tutorDatereg = json['tutor_datereg'];
    tutorCourse = json['tutor_course'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['tutor_id'] = tutorId;
    data['tutor_email'] = tutorEmail;
    data['tutor_phone'] = tutorPhone;
    data['tutor_name'] = tutorName;
    data['tutor_description'] = tutorDesc;
    data['tutor_datereg'] = tutorDatereg;
    data['tutor_course'] = tutorCourse;
    return data;
  }
}