$(document).ready(function () {
  $("#table-user").DataTable({
    ajax: {
      method: "GET",
      url: "api/guest",
      dataSrc: "",
    },
    columns: [
      {
        data: null,
        render: (data, type, row, meta) => {
          return meta.row + 1;
        },
        className: "text-center",
        // data: "id",
      },
      { data: "name" },
      { data: "email" },
      { data: "phone", className: "text-center" },
      {
        data: "user.roles",
        className: "text-center",
        render: function (data, type, row) {
          let roles = "";
          if (data.length > 0) {
            roles += `${data[0].name}`;
          }
          return roles;
        },
      },
      {
        data: null,
        render: (data) => {
          return `
            <div class="d-flex gap-1 justify-content-center">
                <button
                    type="button"
                    class="btn px-4 btn-delete"
                    onclick="deleteGuest(${data.id})"
                >
                <i class="fa-solid fa-trash"></i>
                </button>
            </div>
        `;
        },
      },
    ],
  });
});

// Delete
function deleteGuest(id) {
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
        url: "api/guest/" + id,
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCSRFToken(),
        success: (res) => {
          // console.log(res);

          Swal.fire("Deleted!", "Your Guest has been deleted.", "success");

          $("#table-user").DataTable().ajax.reload();
        },
      });
    }
  });
}

