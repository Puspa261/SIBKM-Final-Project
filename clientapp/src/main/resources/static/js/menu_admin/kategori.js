$(document).ready(function () {
  $("#table-kategori").DataTable({
    ajax: {
      method: "GET",
      url: "api/kategori",
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
      { data: "name" },
      {
        data: null,
        render: (data) => {
          return `
          <div class="d-flex justify-content-center action">
            <div class="d-flex gap-1 justify-content-center">
              <button
                type="button"
                class="btn border-0"
                data-bs-toggle="modal"
                data-bs-target="#updateKategori"
                onclick="updateKategori(${data.id})"
              >
                <i class="fa-solid fa-pen-to-square"></i>
              </button>
            </div>
            <div class="d-flex gap-1 justify-content-center">
              <button
                type="button"
                class="btn border-0"
                onclick="deleteKategori(${data.id})"
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

// Create
$("#create-kategori").click((event) => {
  event.preventDefault();

  let valueName = $(".create-name").val();
  // console.log(valueName);

  $.ajax({
    method: "POST",
    url: "api/kategori",
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify({
      name: valueName,
    }),
    success: (res) => {
      // console.log("berhasil");

      Swal.fire({
        position: "center",
        icon: "success",
        title: "Your Category has been Created...",
        showConfirmButton: false,
        timer: 2000,
      });
      $("#create").modal("hide");
      $(".create-name").val("");
      $("#table-kategori").DataTable().ajax.reload();
    },
    error: (err) => {
      console.log(err);
      Swal.fire({
        position: "center",
        icon: "error",
        title: "Failed Create Category!!",
        showConfirmButton: false,
        timer: 2000,
      });
    },
  });
});

// Update
function updateKategori(id) {
  $.ajax({
    method: "GET",
    url: "api/kategori/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $(".update-id").val(res.id);
      $(".update-name").val(res.name);
    },
    error: (err) => {
      console.log(err);
    },
  });

  $("#update-kategori").click((event) => {
    event.preventDefault();

    let valueName = $(".update-name").val();
    let valueId = $(".update-id").val();
    // console.log(valueName);

    $.ajax({
      method: "PUT",
      url: "api/kategori/" + valueId,
      dataType: "JSON",
      contentType: "application/json",
      beforeSend: addCSRFToken(),
      data: JSON.stringify({
        name: valueName,
      }),
      success: (res) => {
        // console.log("berhasil");

        $("#updateKategori").modal("hide");
        $("#table-kategori").DataTable().ajax.reload();
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Your Category has been Updated...",
          showConfirmButton: false,
          timer: 2000,
        });
      },
      error: (err) => {
        console.log(err);
        Swal.fire({
          position: "center",
          icon: "error",
          title: "Failed Update Category!!",
          showConfirmButton: false,
          timer: 2000,
        });
      },
    });
  });
}

// Delete
function deleteKategori(id) {
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
        url: "api/kategori/" + id,
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCSRFToken(),
        success: (res) => {
          // console.log(res);

          Swal.fire("Deleted!", "Your Category has been Deleted.", "success");

          $("#table-kategori").DataTable().ajax.reload();
        },
      });
    }
  });
}
