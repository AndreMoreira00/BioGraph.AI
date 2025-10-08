from flask import Flask, jsonify, request
import infernal

app = Flask(__name__)
@app.route('/')
def home():
    return 'API'
  
@app.route('/infernal', methods=['POST'])
def infernal_route():
  data = request.json
  sequence = data.get('sequence')

  if not sequence:
      return jsonify({"error": "Sequencia não fornecida"}), 400

  try:
      results = infernal.Infernal(sequence)
      return jsonify(results), 200
  except Exception as e:
      return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    app.run(debug=True)