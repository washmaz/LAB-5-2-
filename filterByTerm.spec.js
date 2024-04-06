function filterByTerm(inputArr, searchTerm) {
  const regex = new RegExp(searchTerm, "i"); 
  return inputArr.filter(function(arrayElement) {
    return arrayElement.url.match(regex);
  });
}

module.exports = filterByTerm;

test("it should filter by a search term (LINK)", () => {
  const input = [
    { id: 1, url: "https://www.url1.dev" },
    { id: 2, url: "https://www.url2.dev" },
    { id: 3, url: "https://www.link3.dev" }
  ];

  const output = [{ id: 3, url: "https://www.link3.dev" }];

  expect(filterByTerm(input, "LINK")).toEqual(output);
});


test("it should handle empty search term", () => {
  const input = [
    { id: 1, url: "https://www.url1.dev" },
    { id: 2, url: "https://www.url2.dev" },
    { id: 3, url: "https://www.link3.dev" }
  ];


  const output = input;

  expect(filterByTerm(input, "")).toEqual(output);
});

  