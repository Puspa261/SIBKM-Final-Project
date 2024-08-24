$(document).ready(function () {
  $("#table-kategori").DataTable({
    ajax: {
      method: "GET",
      url: "api/peminjaman",
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
      { data: "guest.name", className: "text-center" },
      {
        data: "pinjam",
        render: function (data) {
          // Mengonversi tanggal ke format YYYY-MM-DD
          return new Date(data).toISOString().split("T")[0];
        },
        className: "text-center",
      },
      {
        data: "kembali",
        render: function (data) {
          // Mengonversi tanggal ke format YYYY-MM-DD
          return new Date(data).toISOString().split("T")[0];
        },
        className: "text-center",
      },
      { data: "buku.judul" , className: "text-center" },
      { data: "status.status" },
      // {
      //   data: null,
      //   render: (data) => {
      //     return `
      //     <div class="d-flex justify-content-center action">
      //       <div class="d-flex gap-1 justify-content-center">
      //         <button
      //           type="button"
      //           class="btn border-0"
      //           data-bs-toggle="modal"
      //           data-bs-target="#updateKategori"
      //           onclick="updateKategori(${data.id})"
      //         >
      //           <i class="fa-solid fa-pen-to-square"></i>
      //         </button>
      //       </div>
      //       <div class="d-flex gap-1 justify-content-center">
      //         <button
      //           type="button"
      //           class="btn border-0"
      //           onclick="deleteKategori(${data.id})"
      //         >
      //           <i class="fa-solid fa-trash"></i>
      //         </button>
      //       </div>
      //     </div>
      //   `;
      //   },
      // },
    ],
  });
});
