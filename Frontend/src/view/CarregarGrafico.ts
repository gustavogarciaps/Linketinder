import { Chart } from "chart.js/auto";
import { carregarCompetenciasCandidatos } from "../services/ServicoRelacionamento";


export const carregarGrafico = () => {


          const ctx = document.getElementById('carregar_grafico') as HTMLCanvasElement;

          const relacionamentos = carregarCompetenciasCandidatos();

          new Chart(ctx, {
                    type: 'bar',
                    data: {
                              labels: relacionamentos.map(relacionamento => relacionamento.competencia),
                              datasets: [{
                                        label: 'Número de Candidatos/Competência',
                                        data: relacionamentos.map(relacionamento => relacionamento.quantidade),
                                        borderWidth: 1
                              }]
                    },
                    options: {
                              scales: {
                                        y: {
                                                  type: 'linear',
                                                  beginAtZero: true
                                        }
                              },
                              plugins: {

                                        legend: {
                                                  labels: {
                                                            font: {
                                                                      size: 14
                                                            }
                                                  }
                                        }

                              }
                    }
          });

}