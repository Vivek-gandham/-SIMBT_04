const apiKey = "your-api-key";
const submitBtn = document.getElementById("submit");
const locationInput = document.getElementById("location");
const weatherInfo = document.getElementById("weather-info");

submitBtn.addEventListener("click", (event) => {
  event.preventDefault();
  const location = locationInput.value;
  const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${location}&appid=${apiKey}`;

  fetch(apiUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      displayWeatherInfo(data);
    })
    .catch((error) => {
      console.error("There was a problem with the network:", error);
    });
});

function displayWeatherInfo(data) {
  const temperature = data.main.temp;
  const humidity = data.main.humidity;
  const windSpeed = data.wind.speed;
  const weatherDescription = data.weather[0].description;

  const weatherHTML = `
    <div>
      <h2>Current Weather in ${data.name}</h2>
      <p>Temperature: ${temperature} K</p>
      <p>Humidity: ${humidity} %</p>
      <p>Wind Speed: ${windSpeed} m/s</p>
      <p>Weather Description: ${weatherDescription}</p>
    </div>
  `;

  weatherInfo.innerHTML = weatherHTML;
}
