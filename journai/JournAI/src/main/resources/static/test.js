    mapboxgl.accessToken = '';
const map = new mapboxgl.Map({
  container: 'map',
  style: 'mapbox://styles/mapbox/streets-v12',
  center: [-7.900717096503491, 53.416140937630736], // starting position
  zoom: 12
});


// an arbitrary start will always be the same
// only the end or destination will change
const start = [-7.900717096503491, 53.416140937630736];
const end = [-9.062327416832384, 53.275361436594864]
// this is where the code for the next step will go
// create a function to make a directions request
async function getRoute(end) {
  // make a directions request using cycling profile
  // an arbitrary start will always be the same
  // only the end or destination will change
  const query = await fetch(
    `https://api.mapbox.com/directions/v5/mapbox/driving/${start[0]},${start[1]};${end[0]},${end[1]}?steps=true&geometries=geojson&access_token=${mapboxgl.accessToken}`,
    { method: 'GET' }
  );
  const json = await query.json();
  const data = json.routes[0];
  const route = data.geometry.coordinates;
  const geojson = {
    type: 'Feature',
    properties: {},
    geometry: {
      type: 'LineString',
      coordinates: route
    }
  };
  // if the route already exists on the map, we'll reset it using setData
  if (map.getSource('route')) {
    map.getSource('route').setData(geojson);
  }
  // otherwise, we'll make a new request
  else {
    map.addLayer({
      id: 'route',
      type: 'line',
      source: {
        type: 'geojson',
        data: geojson
      },
      layout: {
        'line-join': 'round',
        'line-cap': 'round'
      },
      paint: {
        'line-color': '#3887be',
        'line-width': 5,
        'line-opacity': 0.75
      }
    });
  }
  // add turn instructions here at the end
}

map.on('load', () => {
  // make an initial directions request that
  // starts and ends at the same location
  getRoute(start);

  // Add starting point to the map
  map.addLayer({
    id: 'point',
    type: 'circle',
    source: {
      type: 'geojson',
      data: {
        type: 'FeatureCollection',
        features: [
          {
            type: 'Feature',
            properties: {},
            geometry: {
              type: 'Point',
              coordinates: start
            }
          }
        ]
      }
    },
    paint: {
      'circle-radius': 10,
      'circle-color': '#3887be'
    }
  });




  map.on('click', (event) => {
  const middlePointCoords = [-7.9418, 53.4234]; // Replace with your desired coordinates
  const coords = [-9.044939268056854, 53.28185256095831] //Object.keys(event.lngLat).map((key) => event.lngLat[key]);
  const end = {
    type: 'FeatureCollection',
    features: [
      {
        type: 'Feature',
        properties: {},
        geometry: {
          type: 'Point',
          coordinates: coords
        }
      }
    ]
  };
  if (map.getLayer('end')) {
    map.getSource('end').setData(end);
  } else {
    map.addLayer({
      id: 'end',
      type: 'circle',
      source: {
        type: 'geojson',
        data: {
          type: 'FeatureCollection',
          features: [
            {
              type: 'Feature',
              properties: {},
              geometry: {
                type: 'Point',
                coordinates: coords
              }
            }
          ]
        }
      },
      paint: {
        'circle-radius': 10,
        'circle-color': '#f30'
      }
    });
  }
//  const middlePointCoords = [-7.9418, 53.4234]; // Replace with your desired coordinates

  // Create a GeoJSON feature for the middle point
  const middlePointFeature = {
    type: 'Feature',
    properties: {
      name: 'Middle Point',
      description: 'This is a middle point on the route.',
    },
    geometry: {
      type: 'Point',
      coordinates: middlePointCoords,
    },
  };

  // If the middle point layer already exists, update it with new data
  if (map.getLayer('middle')) {
    map.getSource('middle').setData(middlePointFeature);
  } else {
    // Otherwise, add a new layer for the middle point
    map.addLayer({
      id: 'middle',
      type: 'circle',
      source: {
        type: 'geojson',
        data: {
          type: 'FeatureCollection',
          features: [middlePointFeature],
        },
      },
      paint: {
        'circle-radius': 10,
        'circle-color': '#ff5733', // Choose a color for the middle point
      },
    });
  }

    const entireRoute = [start, middle, end];

  getRoute(entireRoute);
});
