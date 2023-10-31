// cockinfo의 검색 기능

$("#searchBtn").on("click", () => {
   console.log($("#search").val());
   $.ajax({
      url: "search",
      type: "get",
      data: {
         "input": $("#search").val()
      },
      success: (res) => {
         const detail = $("#cocktailPrv");
         detail.html("");

         for (let i = 0; i < res.length; i++) {
            let list = res[i];
            let content = `
                  <details>
                     <summary>${list.cock_name}</summary>
                     <P>${list.cock_desc}</P>
                     <img src="${list.cock_image}" style = "height : 200px; width : 200px">
                  </details>
         `;
            detail.append(content);
         }
      }
   });
});