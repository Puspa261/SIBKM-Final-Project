// Get Profile
// const getUserProfile = () => {
//   const profileId = $("#profile-id");
//   const profileName = $("#profile-name");
//   const profileEmail = $("#profile-email");
//   const profilePhone = $("#profile-phone");
//   const profileRole = $("#profile-role");
//   const profileimage = $("#profile-image");

//   $.ajax({
//     method: "GET",
//     url: "api/guest/profile",
//     dataType: "JSON",
//     contentType: "application/json",
//     success: (res) => {
//       profileimage.attr("src", `${res.image}`);
//       profileId.val(res.id);
//       profileName.text(res.name);
//       profileEmail.val(res.email);
//       profilePhone.val(res.phone);
//       profileRole.val(res.user.roles[0].name);

//       // console.log(res);
//     },
//     error: (err) => {
//       console.log("error = " + err);
//     },
//   });

//   $("#btn-update").click((event) => {
//     event.preventDefault();

//     $.ajax({
//       url: "/api/guest/" + profileId.val(),
//       method: "GET",
//       dataType: "JSON",
//       contentType: "application/json",
//       success: (res) => {
//         // console.log(res);
//         $("#old-image").attr("src", `${res.image}`);

//         $("#update-image").text(res.image);
//         $("#update-id").val(res.id);
//         $("#update-title").text(res.name);
//         $("#update-name").val(res.name);
//         $("#update-email").val(res.email);
//         $("#update-phone").val(res.phone);
//       },
//       error: (err) => {
//         console.log("error = " + err);
//       },
//     });

//     $("#update-profile").click((event) => {
//       event.preventDefault();

//       let updateid = $("#update-id").val();
//       let updatename = $("#update-name").val();
//       let updateemail = $("#update-email").val();
//       let updatephone = $("#update-phone").val();
//       let image = document.getElementById("update-image");

//       // Convert to Base64
//       if (image.files.length > 0) {
//         let imageBook = image.files[0];

//         let reader = new FileReader();
//         (reader.onloadend = function () {
//           let base64Image = reader.result;

//           $.ajax({
//             method: "PUT",
//             url: "api/guest/" + updateid,
//             dataType: "JSON",
//             contentType: "application/json",
//             beforeSend: addCSRFToken(),
//             data: JSON.stringify({
//               name: updatename,
//               email: updateemail,
//               phone: updatephone,
//               image: base64Image,
//             }),
//             success: (res) => {
//               // console.log(res);

//               $("#update").modal("hide");
//               Swal.fire({
//                 position: "center",
//                 icon: "success",
//                 title: "Your Profile has been Updated...",
//                 showConfirmButton: false,
//                 timer: 2000,
//               });
//             },
//             error: (err) => {
//               console.log(err);
//               Swal.fire({
//                 position: "center",
//                 icon: "error",
//                 title: "Failed Update Profile!!",
//                 showConfirmButton: false,
//                 timer: 2000,
//               });
//             },
//           });
//         }),
//           reader.readAsDataURL(imageBook);
//       } else {
//         $.ajax({
//           url: "/api/guest/" + profileId.val(),
//           method: "GET",
//           dataType: "JSON",
//           contentType: "application/json",
//           success: (res) => {
//             // console.log(res.image);

//             $.ajax({
//               method: "PUT",
//               url: "api/guest/" + updateid,
//               dataType: "JSON",
//               contentType: "application/json",
//               beforeSend: addCSRFToken(),
//               data: JSON.stringify({
//                 name: updatename,
//                 email: updateemail,
//                 phone: updatephone,
//                 image: base64Image,
//               }),
//               success: (res) => {
//                 // console.log(res);

//                 $("#update").modal("hide");
//                 Swal.fire({
//                   position: "center",
//                   icon: "success",
//                   title: "Your Profile has been Updated...",
//                   showConfirmButton: false,
//                   timer: 2000,
//                 });
//               },
//               error: (err) => {
//                 console.log(err);
//                 Swal.fire({
//                   position: "center",
//                   icon: "error",
//                   title: "Failed Update Profile!!",
//                   showConfirmButton: false,
//                   timer: 2000,
//                 });
//               },
//             });
//           },
//         });
//       }
//     });
//   });
// };

// // Sidebar
// $(document).ready(function () {
//   // console.log("halo");

//   const profileName = $("#profile-name");
//   const profileRole = $("#profile-role");
//   const profileimages = $("#profile-img");

//   $.ajax({
//     method: "GET",
//     url: "api/guest/profile",
//     dataType: "JSON",
//     contentType: "application/json",
//     success: (res) => {
//       profileName.text(res.name);
//       profileRole.text(res.user.roles[0].name);
//       profileimages.attr("src", `${res.image}`);

//       console.log(res);
//     },
//     error: (err) => {
//       console.log("error = " + err);
//     },
//   });
// });

// Get Profile
const getUserProfile = () => {
  const profileId = $("#profile-id");
  const profileName = $("#profile-name");
  const profileEmail = $("#profile-email");
  const profilePhone = $("#profile-phone");
  const profileRole = $("#profile-role");
  const profileimage = $("#profile-image");

  $.ajax({
    method: "GET",
    url: "api/guest/profile",
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      profileimage.attr("src", `${res.image}`);
      profileId.val(res.id);
      profileName.text(res.name);
      profileEmail.val(res.email);
      profilePhone.val(res.phone);
      profileRole.val(res.user.roles[0].name);

      // console.log(res);
    },
    error: (err) => {
      console.log("error = " + err);
    },
  });

  $("#btn-update").click((event) => {
    event.preventDefault();

    $.ajax({
      url: "/api/guest/" + profileId.val(),
      method: "GET",
      dataType: "JSON",
      contentType: "application/json",
      success: (res) => {
        // console.log(res);
        $("#old-image").attr("src", `${res.image}`);

        $("#update-image").text(res.image);
        $("#update-id").val(res.id);
        $("#update-title").text(res.name);
        $("#update-name").val(res.name);
        $("#update-email").val(res.email);
        $("#update-phone").val(res.phone);
      },
      error: (err) => {
        console.log("error = " + err);
      },
    });

    $("#update-profile").click((event) => {
      event.preventDefault();

      let updateid = $("#update-id").val();
      let updatename = $("#update-name").val();
      let updateemail = $("#update-email").val();
      let updatephone = $("#update-phone").val();
      let image = document.getElementById("update-image");

      // Convert to Base64
      if (image.files.length > 0) {
        let imageBook = image.files[0];

        let reader = new FileReader();
        (reader.onloadend = function () {
          let base64Image = reader.result;

          $.ajax({
            method: "PUT",
            url: "api/guest/" + updateid,
            dataType: "JSON",
            contentType: "application/json",
            beforeSend: addCSRFToken(),
            data: JSON.stringify({
              name: updatename,
              email: updateemail,
              phone: updatephone,
              image: base64Image,
            }),
            success: (res) => {
              // console.log(res);

              $("#update").modal("hide");
              Swal.fire({
                position: "center",
                icon: "success",
                title: "Your Profile has been Updated...",
                showConfirmButton: false,
                timer: 2000,
              });
            },
            error: (err) => {
              console.log(err);
              Swal.fire({
                position: "center",
                icon: "error",
                title: "Failed Update Profile!!",
                showConfirmButton: false,
                timer: 2000,
              });
            },
          });
        }),
          reader.readAsDataURL(imageBook);
      } else {
        $.ajax({
          url: "/api/guest/" + profileId.val(),
          method: "GET",
          dataType: "JSON",
          contentType: "application/json",
          success: (res) => {
            // console.log(res.image);

            $.ajax({
              method: "PUT",
              url: "api/guest/" + updateid,
              dataType: "JSON",
              contentType: "application/json",
              beforeSend: addCSRFToken(),
              data: JSON.stringify({
                name: updatename,
                email: updateemail,
                phone: updatephone,
                image: base64Image,
              }),
              success: (res) => {
                // console.log(res);

                $("#update").modal("hide");
                Swal.fire({
                  position: "center",
                  icon: "success",
                  title: "Your Profile has been Updated...",
                  showConfirmButton: false,
                  timer: 2000,
                });
              },
              error: (err) => {
                console.log(err);
                Swal.fire({
                  position: "center",
                  icon: "error",
                  title: "Failed Update Profile!!",
                  showConfirmButton: false,
                  timer: 2000,
                });
              },
            });
          },
        });
      }
    });
  });
};
