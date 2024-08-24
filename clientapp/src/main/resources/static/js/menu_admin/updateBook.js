// Update
$(document).ready(function () {
  // Ambil ID buku dari URL
  const urlParams = new URLSearchParams(window.location.search);
  const id = urlParams.get("id");

  // console.log("ID Buku:", id);

  $.ajax({
    method: "GET",
    url: "api/kategori/",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: (res) => {
      const selectElement = $("#update-category-book");
      selectElement.empty();

      res.forEach((kategori) => {
        const option = `<option value="${kategori.id}">${kategori.name}</option>`;
        selectElement.append(option);
      });

      $.ajax({
        method: "GET",
        url: "api/buku/" + id,
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
          // console.log(res);
          $("#update-id").val(res.id);
          $("#update-judul").val(res.judul);
          $("#update-pengarang").val(res.pengarang);
          $("#update-penerbit").val(res.penerbit);
          $("#update-tahun").val(res.tahunTerbit);
          $("#update-isbn").val(res.isbn);
          $("#update-sinopsis").val(res.sinopsis);
          $("#update-category-book").val(res.kategori.id);
          // $("#update-image").text(res.image);

          $("#old-image-book").attr("src", `${res.image}`);
          // console.log(res.image);
        },
        error: (err) => {
          console.log(err);
        },
      });

      $("#submitBtn").click((event) => {
        event.preventDefault();

        let id = $("#update-id").val();
        let judul = $("#update-judul").val();
        let pengarang = $("#update-pengarang").val();
        let penerbit = $("#update-penerbit").val();
        let tahun = $("#update-tahun").val();
        let isbn = $("#update-isbn").val();
        let sinopsis = $("#update-sinopsis").val();
        let kategori = $("#update-category-book").val();
        let image = document.getElementById("update-image");

        // Convert to Base64
        if (image.files.length > 0) {
          let imageBook = image.files[0];

          let reader = new FileReader();
          reader.onloadend = function () {
            let base64Image = reader.result;

            $.ajax({
              method: "PUT",
              url: "api/buku/" + id,
              dataType: "JSON",
              contentType: "application/json",
              beforeSend: addCSRFToken(),
              data: JSON.stringify({
                judul: judul,
                pengarang: pengarang,
                penerbit: penerbit,
                tahunTerbit: tahun,
                isbn: isbn,
                sinopsis: sinopsis,
                kategori: { id: kategori },
                image: base64Image,
              }),
              success: (res) => {
                // $("#updateBook").modal("hide");
                Swal.fire({
                  position: "center",
                  icon: "success",
                  title: "Your Book has been Updated...",
                  showConfirmButton: true,
                }).then((result) => {
                  if (
                    result.isConfirmed ||
                    result.dismiss === Swal.DismissReason.close
                  ) {
                    window.location.href = "/book";
                    $("#table-buku").DataTable().ajax.reload();
                  }
                });
              },
            });
          };
          reader.readAsDataURL(imageBook);
        } else {
          // console.log("error gambar");

          $.ajax({
            method: "GET",
            url: "api/buku/" + id,
            dataType: "JSON",
            contentType: "application/json",
            success: (res) => {
              // console.log(res.image);

              $.ajax({
                method: "PUT",
                url: "api/buku/" + id,
                dataType: "JSON",
                contentType: "application/json",
                beforeSend: addCSRFToken(),
                data: JSON.stringify({
                  judul: judul,
                  pengarang: pengarang,
                  penerbit: penerbit,
                  tahunTerbit: tahun,
                  isbn: isbn,
                  sinopsis: sinopsis,
                  kategori: { id: kategori },
                  image: res.image,
                }),
                success: (res) => {
                  // $("#updateBook").modal("hide");
                  Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Your Book has been Updated...",
                    showConfirmButton: true,
                  }).then((result) => {
                    if (
                      result.isConfirmed ||
                      result.dismiss === Swal.DismissReason.close
                    ) {
                      window.location.href = "/book";
                      $("#table-buku").DataTable().ajax.reload();
                    }
                  });
                },
              });
            },
          });
        }
      });
    },
    error: (err) => {
      console.log(err);
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Failed Update book!!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});
