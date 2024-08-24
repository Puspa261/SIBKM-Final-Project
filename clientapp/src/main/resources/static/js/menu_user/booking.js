$(document).ready(function () {
  const idGuest = $("#userId");
  const nameGuest = $("#userName");

  $.ajax({
    method: "GET",
    url: "api/guest/profile",
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      idGuest.val(res.id);
      nameGuest.val(res.name);

      $.ajax({
        method: "GET",
        url: "api/buku",
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
          const dropdown = $("#list-buku");
          res.forEach((buku) => {
            const option = `<option value="${buku.id}">${buku.judul}</option>`;
            dropdown.append(option);
          });
        },
      });

      $.ajax({
        method: "GET",
        url: "api/status",
        dataType: "JSON",
        contentType: "application/json",
        success: (res) => {
          const dropdown = $("#list-status");
          res.forEach((status) => {
            const option = `<option value="${status.id}">${status.status}</option>`;
            dropdown.append(option);
          });
        },
      });

      $("#bookingBtn").click((event) => {
        event.preventDefault();

        const userIdInput = document.getElementById("userId");
        const tglPinjamInput = document.getElementById("tgl_pinjam");
        const tglKembaliInput = document.getElementById("tgl_kembali");
        const listBukuInput = document.getElementById("list-buku");
        // console.log(id);

        $.ajax({
          method: "GET",
          url: "api/peminjaman",
          dataType: "JSON",
          contentType: "application/json",
          success: (res) => {
            // console.log(res);

            let checkStatusId = true;

            res.forEach(function (data) {
              if (parseInt(data.guest.id) === parseInt(userIdInput.value)) {
                // console.log(data.guest.id);

                if (data.status.id === 1) {
                  checkStatusId = false;
                }
              }
            });

            // console.log(checkStatusId);
            if (checkStatusId) {
              // console.log("boleh minjem");
              let unifyData = {
                pinjam: tglPinjamInput.value,
                kembali: tglKembaliInput.value,
                buku: {
                  id: parseInt(listBukuInput.value),
                },
                guest: {
                  id: parseInt(userIdInput.value),
                },
                status: {
                  id: 1,
                },
              };
              $.ajax({
                method: "POST",
                url: "api/peminjaman",
                dataType: "JSON",
                contentType: "application/json",
                beforeSend: addCSRFToken(),
                data: JSON.stringify(unifyData),
                success: (res) => {
                  // console.log(res);
                  Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Booking has been success!",
                    showConfirmButton: true,
                  }).then((result) => {
                    if (
                      result.isConfirmed ||
                      result.dismiss === Swal.DismissReason.close
                    ) {
                      window.location.href = "/history";
                    }
                  });
                },
              });
            } else {
              // console.log("gaboleh minjem");
              Swal.fire({
                position: "center",
                icon: "error",
                title: "Please return the book!!",
                showConfirmButton: true,
              }).then((result) => {
                if (
                  result.isConfirmed ||
                  result.dismiss === Swal.DismissReason.close
                ) {
                  window.location.href = "/history";
                }
              });
            }
          },
        });
      });
    },
  });
});
