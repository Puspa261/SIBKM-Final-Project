$(document).ready(function () {
  $.ajax({
    url: "api/buku",
    success: (res) => {
      // console.log(res);

      let books = "";

      $.each(res, (key, val) => {
        // console.log(val);

        books += `
          <div
              class="card hover-card m-3 card-dashboard"
              data-bs-toggle="modal"
              data-bs-target="#book"
              onclick="detailBuku('${val.id}')"
          >
              <div class="bg-book">
                <img
                    src="${val.image}"
                    class="card-img-top img-background my-5"
                    alt="..."
                />
                <img
                    src="${val.image}"
                    class="card-img-top img-dashboard"
                    alt="..."
                />
              </div>
              <div class="card-body">
                  <p class="card-title subtitle-book text-start mt-3">${val.kategori.name}</p>
                  <p class="card-title title-book text-start fw-bold">${val.judul}</p>
              </div>
          </div>
          `;
      });

      $("#books").html(books);
    },
  });
});




// Detail Buku
function detailBuku(id) {
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


