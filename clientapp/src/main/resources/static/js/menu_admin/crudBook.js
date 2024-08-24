$(document).ready(function () {
  $("#table-buku").DataTable({
    ajax: {
      method: "GET",
      url: "api/buku",
      dataSrc: "",
    },
    columns: [
      {
        data: null,
        render: (data, type, row, meta) => {
          return meta.row + 1;
        },
        className: "text-center",
      },
      { data: "judul" },
      { data: "kategori.name" },
      { data: "tahunTerbit", className: "text-center" },

      {
        data: null,
        render: (data) => {
          return `
            <div class="d-flex justify-content-center">
                
                <div class="d-flex gap-1 justify-content-center">
                <button
                    type="button"
                    class="btn px-4 border-0"
                    data-bs-toggle="modal"
                    data-bs-target="#detail-book"
                    onclick="getById(${data.id})"
                >
                    <i class="fa-solid fa-circle-info fa-lg"></i>
                </button>
                </div>

                <div class="d-flex gap-1 justify-content-center">
                <button
                    type="button"
                    class="btn px-4 border-0"
                    onclick="window.location.href='/updateBook?id=${data.id}';"
                >
                    <i class="fa-solid fa-pen-to-square"></i>
                </button>

                </div>

                <div class="d-flex gap-1 justify-content-center">
                <button
                    type="button"
                    class="btn px-4 border-0"
                    onclick="deleteBook(${data.id})"
                >
                    <i class="fa-solid fa-trash"></i>
                </button>
                </div>
            </div>
            `;
        },
      },
    ],
  });
});

// Get By Id
function getById(id) {
  $.ajax({
    method: "GET",
    url: "api/buku/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#detail-img").attr("src", `${res.image}`);
      $("#detail-id").val(`${res.id}`);
      $("#detail-judul").text(`${res.judul}`);
      $("#detail-pengarang").text(`${res.pengarang}`);
      $("#detail-penerbit").text(`${res.penerbit}`);
      $("#detail-tahun").text(`${res.tahunTerbit}`);
      $("#detail-isbn").text(`${res.isbn}`);
      $("#detail-category").text(`${res.kategori.name}`);
      $("#detail-sinopsis").text(`${res.sinopsis}`);
    },
    error: (err) => {
      console.log(err);
    },
  });
}

// Create
$(document).ready(function () {
  $.ajax({
    method: "GET",
    url: "api/kategori/",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: (res) => {
      // console.log(res);
      const dropdown = $("#create-category-book");

      res.forEach((kategori) => {
        const option = `<option value="${kategori.id}">${kategori.name}</option>`;
        dropdown.append(option);
      });
      // console.log(res);

      $("#submitBtn").click((event) => {
        event.preventDefault();

        let judul = $("#create-judul").val();
        let pengarang = $("#create-pengarang").val();
        let penerbit = $("#create-penerbit").val();
        let tahun = $("#create-tahun").val();
        let isbn = $("#create-isbn").val();
        let sinopsis = $("#create-sinopsis").val();
        let kategori = $("#create-category-book").val();
        let image = document.getElementById("create-image");

        if (image.files.length > 0) {
          let imageBook = image.files[0];

          let reader = new FileReader();
          reader.onloadend = function () {
            let base64Image = reader.result;

            $.ajax({
              method: "POST",
              url: "api/buku/",
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
                // console.log(res);

                Swal.fire({
                  position: "center",
                  icon: "success",
                  title: "Your Book has been Created!!",
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
              error: (err) => {
                console.log(err);
                Swal.fire({
                  position: "center",
                  icon: "error",
                  title: "Failed Create book!!",
                  showConfirmButton: false,
                  timer: 2000,
                });
              },
            });
          };

          reader.readAsDataURL(imageBook);
        } else {
          console.log("error");
        }
      });
    },
    error: (err) => {
      console.log(err);
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Failed Create book!!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

// Delete
function deleteBook(id) {
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        method: "DELETE",
        url: "api/buku/" + id,
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCSRFToken(),
        success: (res) => {
          // console.log(res);

          Swal.fire("Deleted!", "Your Book has been Deleted.", "success");

          $("#table-buku").DataTable().ajax.reload();
        },
      });
    }
  });
}
