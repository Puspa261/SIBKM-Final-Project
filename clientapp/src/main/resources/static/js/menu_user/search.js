$(document).ready(function () {
  // console.log("halo");
  let kategoriId = "";
  let books = "";
  $.ajax({
    method: "GET",
    url: "api/kategori",
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      //   console.log(res);

      res.forEach((kategori) => {
        // console.log(kategori);
        kategoriId = kategori.id;
        // console.log(kategoriId);

        let dropdownItem = `
          <button class="dropdown-item dropdown-kategori" type="button" data-category="${kategori.id}">${kategori.name}</button>
          `;

        $("#dropdownMenu2").next(".dropdown-menu").append(dropdownItem);
      });

      $(".dropdown-kategori").on("click", function () {
        let findId = $(this).data("category");
        // console.log(findId);

        $.ajax({
          method: "GET",
          url: "api/buku",
          dataType: "JSON",
          contentType: "application/json",
          success: (res) => {
            // console.log(res);

            let filteredData = res.filter(
              (item) => item.kategori.id === findId
            );

            // console.log(filteredData);

            $("#carouselExampleAutoplaying").empty();

            books = "";
            filteredData.forEach((data) => {
              books += `
                <div
                    class="card hover-card m-3 card-dashboard"
                    data-bs-toggle="modal"
                    data-bs-target="#book"
                    onclick="detailBuku('${data.id}')"
                >
                    <div class="bg-book">
                        <img
                            src="${data.image}"
                            class="card-img-top img-background my-5"
                            alt="..."
                        />
                        <img
                            src="${data.image}"
                            class="card-img-top img-dashboard"
                            alt="..."
                        />
                    </div>
                    <div class="card-body">
                        <p class="card-title subtitle-book text-start mt-3">${data.kategori.name}</p>
                        <p class="card-title title-book text-start fw-bold">${data.judul}</p>
                    </div>
                </div>
                `;
            });
            $("#books").html(books);
            // window.location.href = "/search";
          },
        });
      });
    },
  });
});
