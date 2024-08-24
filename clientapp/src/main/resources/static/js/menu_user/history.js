$(document).ready(function () {
  $.ajax({
    method: "GET",
    url: "api/guest/profile",
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      let userId = res.id;

      $.ajax({
        method: "GET",
        url: "api/peminjaman",
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
          // console.log("data peminjaman : ");
          console.log(res);

          const filteredData = res.filter((item) => item.guest.id === userId);

          // console.log("Data peminjaman untuk guest.id === userId:");
          // console.log(filteredData);

          let dataTableArray = [];

          filteredData.forEach((data, index) => {
            // console.log("ini id untuk keperluan CRUD : " + data.id);

            let dataTable = [
              index + 1,
              extractDateFromTimestamp(data.pinjam),
              extractDateFromTimestamp(data.kembali),
              data.buku.judul,
              data.status.status,
              `<button class="btn btn-outline-dark px-4 py-1 mx-2" onclick="handleUpdate(${index}, '${data.id}')">Return</button>`,
            ];
            dataTableArray.push(dataTable);
          });

          $("#table-kategori").DataTable({
            data: dataTableArray,
            columns: [
              { title: "No", className: "text-center" },
              { title: "Tanggal Pinjam", className: "text-center" },
              { title: "Tanggal Kembali", className: "text-center" },
              { title: "Buku" },
              { title: "Status" },
              { title: "Action" },
            ],
          });
        },
      });
    },
  });
});

function handleUpdate(index, id) {
  let valueId = $(".update-id");
  let valuePinjam = $(".update-pinjam");
  let valueKembali = $(".update-kembali");
  let valueBuku = $(".update-buku");
  let valueGuest = $(".update-guest");
  let valueStatus = $(".update-status");

  $.ajax({
    method: "GET",
    url: "api/peminjaman/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      // console.log(res);

      valueId.val(res.id);
      valuePinjam.val(res.pinjam);
      valueKembali.val(res.kembali);
      valueBuku.val(res.buku.id);
      valueGuest.val(res.guest.id);
      valueStatus.val(res.status.id);

      // console.log(valueId.val());
      // console.log(valuePinjam.val());
      // console.log(valueKembali.val());
      // console.log(valueBuku.val());
      // console.log(valueGuest.val());
      // console.log(valueStatus.val());

      let updateData = {
        pinjam: valuePinjam.val(),
        kembali: valueKembali.val(),
        buku: {
          id: parseInt(valueBuku.val()),
        },
        guest: {
          id: parseInt(valueGuest.val()),
        },
        status: {
          id: 2,
        },
      };

      console.log(updateData);

      $.ajax({
        method: "PUT",
        url: "api/peminjaman/" + valueId.val(),
        dataType: "JSON",
        contentType: "application/json",
        beforeSend: addCSRFToken(),
        data: JSON.stringify(updateData),
        success: (res) => {
          // $("#table-kategori").DataTable().ajax.reload();
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Your Book has been Returned...",
            showConfirmButton: true,
          }).then((result) => {
            if (
              result.isConfirmed ||
              result.dismiss === Swal.DismissReason.close
            ) {
              location.reload();
            }
          });
        },
      });
    },
    error: (err) => {
      console.error("Error fetching data:", err);
    },
  });
}

function extractDateFromTimestamp(timestamp) {
  const dateObject = new Date(timestamp);
  const year = dateObject.getFullYear();
  const month = (dateObject.getMonth() + 1).toString().padStart(2, "0");
  const day = dateObject.getDate().toString().padStart(2, "0");

  return `${year}-${month}-${day}`;
}
