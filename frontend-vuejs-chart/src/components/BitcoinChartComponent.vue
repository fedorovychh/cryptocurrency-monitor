<template>
    <div class="btcChart">
      <div class="specifiedDate">
        <div class="dateSpecification">
          <h2 class="dateSpecificationText">Please, specify start/end dates:</h2>
          <div class="dateBlock">
            <div class="startDateText dateElement">Enter start day</div>
            <input type="date" name="startDate" id="startDate" class="startDate dateElement">
          </div>
          <div class="dateBlock">
            <div class="endDateText dateElement">Enter end day</div>
            <input type="date" name="endDate" id="endDate" class="endDate dateElement">
          </div>
        </div>
        <div class="specifyDateButtonBox">
          <button type="button" class="specifyDateButton" id="specifyDateButton" v-on:click="update">Get by dates</button>
        </div>
      </div>
      <div class="chartBox">
          <h2>Bitcoin/USD chart</h2>
          <canvas id="canvas-chart"></canvas>
      </div>  
    </div>
</template>

<style type="text/css">
  .btcChart {
    margin: 12px;
    padding: 40px;
    background: rgba(221, 221, 221, 0.1);
    border: 4px solid;
  }
  .dateSpecification {
    display: flex;
    width: fit-content;
    margin: auto;
    padding: 12px;
    border: 0.7px solid rgba(187, 187, 187, 0.7);
    text-align: center;
  }
  .dateSpecificationText {
    align-self: center;
  }
  .dateBlock {
    margin: 6px;
    padding: 6px;
  }
  .dateElement {
    padding: 6px;
  }
  input {
    border: 0.5px solid rgba(187, 187, 187, 0.3);
  }
  .specifyDateButtonBox {
    text-align: center;
  }
  .specifyDateButton {
    margin: 12px;
    padding: 6px;
  }
  .chartBox {
    margin: auto;
    max-height: 1200px;
    max-width: 1200px;
    padding: 20px;
    text-align: center;
  }
</style>

<script setup>
  import Chart from 'chart.js/auto'
  import { onMounted } from 'vue';
  import moment from 'moment';

  const getData = async (inputStartDate, inputEndDate) => {
    const baseUrl = `http://localhost:8080/api/results?startDate=${inputStartDate}&endDate=${inputEndDate}`;
    const res = await fetch(baseUrl);
    const data = await res.json();
    return data;
  }

  var prices = [];
  var days = [];

  var data = {  
      labels: prices,
      datasets: [{
        label: 'BTC/USD price',
        backgroundColor: 'rgb(255, 230, 0)',
        borderColor: 'rgb(0, 0, 0)',
        data: days,
      }]
    };

  var config = {
    type: 'line',
    data: data,
    options: {
      layout: {
          padding: {
              left: 50,
              right: 50,
              top: 50,
              bottom: 50
          }
      }
    }
  };

  function clearData() {
    prices.length = 0
    days.length = 0
  }

  function addDays(date) {
    const newDate = new Date(date.setDate(date.getDate() + 1));
    return newDate.toISOString().slice(0, 10);
  }

  function getStartPeriodDate(dateString) {
    const date = new Date(dateString);
    return new Date(date.setDate(date.getDate() - 1));
  }

  function proceedData(fetchedData, startDate) {
    const parsedDate = getStartPeriodDate(startDate);
    for (let i = 0; i < fetchedData.length; i++) {
      prices.push(addDays(parsedDate));
      days.push(fetchedData[i].closePrice);
    }
  }

  function destroyChart() {
    let chartStatus = Chart.getChart("canvas-chart");
    if (chartStatus != undefined) {
      chartStatus.destroy();
    }
  }

  onMounted(async () => {
    const defaultStartDate = "2024-02-15";
    const defaultEndDate = "2024-03-11";
    const fetchedData = await getData(defaultStartDate, defaultEndDate);

    proceedData(fetchedData, defaultStartDate);

    const canvasTag = document.getElementById('canvas-chart')
    new Chart(
      canvasTag,
      config
    );
  })

  async function update() {
    const inputStartDate = document.getElementById('startDate');
    const inputEndDate = document.getElementById('endDate');
    if (moment(inputStartDate.value).isAfter(moment(inputEndDate.value))) {
      alert("Please, specify correct dates!");
      return;
    }
    const fetchedData = await getData(inputStartDate.value, inputEndDate.value);

    clearData();
    proceedData(fetchedData, inputStartDate.value);
    destroyChart();

    const canvasTag = document.getElementById('canvas-chart');
    new Chart(
      canvasTag,
      config
    );
  }
</script>
