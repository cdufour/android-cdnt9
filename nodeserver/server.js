const express = require('express')
const bodyParser = require('body-parser')
const app = express()

var users = []
var id = 0

app.use(bodyParser.json()) // middleware intercepts request

app.get('/', (req, res) => res.send('ok'))

app.post('/signup', (req, res) => {
	console.log(req.body)
	const { email, password } = req.body

	if (!email || !password) return res.status(200).json({message: "MISSING_DATA"})

	var found = users.find(user => user.email == email)
	if (found) return res.status(409).json({message: "EMAIL_FOUND"})

	id++;
	users.push({ id, email, password })
	res.json({message: "USER_REGISTERED"})
	console.log(users)
}) 

app.listen(3000, () => console.log('Server running on 3000...'))
