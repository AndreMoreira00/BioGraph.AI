from flask import Flask, jsonify, request
import infernal
import rfam_search_family

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

@app.route('/get_molecules_by_rfam', methods=['GET'])
def rfam_search_family_route():
  data = request.json
  rfam_acc = data.get('rfam_acc')

  if not rfam_acc:
      return jsonify({"error": "Familia não fornecida"}), 400

  try:
      results = rfam_search_family.get_molecules_by_rfam(rfam_acc)
      return jsonify(results), 200
  except Exception as e:
      return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    app.run(debug=True)