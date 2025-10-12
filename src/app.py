from flask import Flask, jsonify, request
import infernal
import rfam_search_family
import biograpth_api

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
def get_molecules_by_rfam():
  rfam_acc = request.args.get('rfam_acc')

  if not rfam_acc:
      return jsonify({"error": "Familia não fornecida"}), 400

  try:
      results = rfam_search_family.get_molecules_by_rfam(rfam_acc)
      return jsonify(results), 200
  except Exception as e:
      return jsonify({"error": str(e)}), 500

@app.route('/get_data_by_rfam_and_chain', methods=['GET'])
def get_data_by_rfam_and_chain():
  rfam_acc = request.args.get('rfam_acc')
  chain = request.args.get('chain')

  if not rfam_acc:
      return jsonify({"error": "Familia não fornecida"}), 400

  try:
      results = rfam_search_family.get_data_by_rfam_and_chain(rfam_acc, chain)
      return jsonify(results), 200
  except Exception as e:
      return jsonify({"error": str(e)}), 500
  
@app.route('/get_structure_by_chain_pdb_id_start_end', methods=['GET'])
def get_structure_by_chain_pdb_id_start_and_end():
  chain = request.args.get('chain')
  pdb_id = request.args.get('pdb_id')
  pdb_start = request.args.get('pdb_start')
  pdb_end = request.args.get('pdb_end')

  if not pdb_id or not pdb_start or not pdb_end or not chain:
      return jsonify({"error": "Parâmetros insuficientes"}), 400

  try:
      structure = biograpth_api.get_structure_by_chain_pdb_id_start_end(chain=chain,pdb_id=pdb_id,pdb_start=pdb_start,pdb_end=pdb_end)
      return jsonify(structure), 200
  except Exception as e:
      return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    app.run(debug=True)