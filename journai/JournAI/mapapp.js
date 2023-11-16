const API_KEY = //your api key


//const (name of variable can be anything) = document.queryselector (id of what you are looking for)
//document.queryselector looks through the html to find the thing you are searching for
const submitButton = document.querySelector('#submit')
const outPutElement = document.querySelector('#output')
const inputElement = document.querySelector('input')
const historyElement = document.querySelector('.history')
const buttonElement = document.querySelector('button')


//changes the "input box" when user selects on an old chat from the history side box
function changeInput(value){
    const inputElement = document.querySelector('input')
    inputElement.value = (value)
}

//this is from openai api
//only thing mine is the clicked which will log when a click happens
async function getMessage(){
    console.log('clicked')
    const options = {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ${API_KEY}',
            'Content-Type: application/json'
        },
        body:JSON.stringify({
            model: "gpt-3.5-turbo",
            messages: [{
                role: "system",
                content: "You are a helpful assistant that provides a driving routes based on user input."
            },

                    {role: "user",
                    content: inputElement.value}]
        })
    }
    try{
        const response = await fetch ('https://api.openai.com/v1/chat/completions', options)
        const data = await response.json()
        console.log(data)
        outPutElement.textContent = data.choices[0].message.content
        if (data.choices[0].message.content) {
            //this is just changing the input box when a historic chat is chosen
            const pElement = document.createElement('p')
            pElement.textContent = inputElement.value
            pElement.addEventListener('click', () => changeInput(pElement.textContent))
            historyElement.append(pElement)
        }
    }
    catch (error){
        console.error(error)

    }
}
//when submit button is clicked put out a message
submitButton.addEventListener('click', getMessage)

function clearInput () {
    inputElement.value = ''
}
submitButton.addEventListener('click', clearInput)