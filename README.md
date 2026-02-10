# Módulo 2: Notificações e Alertas

## Descrição
Este módulo tem como objetivo manter o usuário informado de forma proativa sobre eventos importantes de sua viagem. A equipe será responsável por implementar um sistema de notificações push que alerte o usuário sobre a proximidade do ônibus, mudanças de trajeto e lotação do veículo.

## Requisitos Funcionais
A equipe deve desenvolver as seguintes funcionalidades, com base na documentação original:

- RF23: Alerta de Embarque: O aplicativo deve enviar uma notificação push para o usuário quando o **ônibus** que ele pretende pegar estiver se aproximando do **ponto de embarque** selecionado. O gatilho para a notificação será a saída do ônibus da última **parada anterior** ao ponto do usuário. (Citação: "O aplicativo deve enviar notificações (push) ao usuário quando o seu ônibus estiver próximo... do ponto de embarque selecionado. (Notificação será feita assim que o onibus sair da última parada anterior à de destino)")
- RF24: Alerta de Modificação de Trajeto: Caso ocorra uma modificação inesperada no **trajeto de uma linha** de interesse do usuário (como um desvio ou interdição), o aplicativo deve notificá-lo. A notificação deve dar ao usuário a opção de continuar com a viagem (atualizando as informações) ou cancelar a solicitação de **embarque**. (Citação: "O aplicativo deve notificar o usuário caso ocorra uma modificação inesperada... no trajeto de uma linha de seu interesse e perguntar se o usuário quer continuar com o pedido ou cancelar a solicitação de embarque.")
- RF25: Alerta de Lotação: O aplicativo deve fornecer uma estimativa da **lotação do ônibus** que está se aproximando. Essa informação será visual, representada pela coloração da miniatura do ônibus no mapa, e calculada com base na contagem de solicitações de embarque feitas por todos os usuários do aplicativo para aquele veículo. (Citação: "O aplicativo deve notificar a lotação estimada do ônibus que está se aproximando (por meio da coloração da miniatura no mapa). Tal parâmetro é medido com base na contagem de solicitações de embarque...")
## Requisitos Não Funcionais a Considerar
- Desempenho (RNF01): O envio de notificações deve ocorrer em tempo real, com latência mínima.
- Usabilidade (RNF05): As notificações devem ser claras, concisas e relevantes para o usuário. Deve ser possível para o usuário gerenciar as **preferências de notificação**.
- Conectividade (RNF06): O sistema de notificações deve ser resiliente a falhas de conexão, garantindo a entrega das mensagens assim que a conexão for restabelecida.
- Escalabilidade (RNF07): A infraestrutura deve ser capaz de lidar com o envio de um grande volume de notificações simultâneas para milhares de usuários.
- Compatibilidade (RNF03): O serviço de notificação push deve ser compatível e funcional tanto em dispositivos Android quanto iOS

Glossario: 

Linha: É uma rota por onde o unibus percorre recolhendo os passageiros
Ponto: local de embarque na linha 
Rota desvio: Uma rota secundaria para contornar algum evento e retornar a linha principal