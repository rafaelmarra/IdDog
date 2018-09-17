# IdDog
Desafio IdWall (https://github.com/idwall/desafios-iddog)


Funcionamento:

1. O aplicativo é aberto dentro de uma tela de login, com um campo de texto para ser preenchido. A activity coleta o texto de e-mail preenchido ao clicar do botão de Login e faz um requisição do tipo POST para a API do desafio por meio da biblioteca Volley.

2. A requisição passa para a API um header "Content-Type: application/json" e um body em formato raw com o email informado pelo usuário. Em caso de erro, um toast informa o usuário de que o email é inválido e nada mais acontece. Caso ocorra uma resposta positiva do servidor, a token gerada é coletada e passada para a próxima activity via Bundle, assim como a próxima activity é invocada.

3. Na criação da atividade seguinte, são feitas 4 requisições do tipo GET para a API, cada uma contendo uma das possíveis "category". No projeto contido neste repositório, no entanto, não foi possível resolver uma falha, na qual as requisições feitas pelo Volley retornavam com o status de acesso negado, mesmo que, a mesma requisição com os mesmos parâmetros feita pelo aplicativo Postman obteve sucesso. Infelizmente, pelo final do prazo, não foi possível continuar buscando soluções para esta situação.

4. A resposta de cada uma das solicitações GET preencheria uma arrayList com as URLs das imagens, para que possam ser exibidas na Activity. Foi utilizado um TabLayout, com 4 abas superiores, cada uma das abas contendo uma das categorias. Cada aba contem uma RecyclerView que utiliza um GridLayout. Seriam exibidas 2 imagens por linha da lista.

5. A associação de cada URL com cada uma das imageViews seria feita com a biblioteca Picasso, que permite uma associação com poucas linhas de código e permite também o uso de uma imagem placeholder, em caso de erro.

6. Por fim, as imagens seriam clicáveis com o uso de uma interface, definindo um método onClick para cada ImageView. O ato de clicar abriria uma nova Activity, que exibiria a imagem clicada em tela cheia. O método finish() seria invocado ao retornar à activity anterior, visando a economia de recursos.
