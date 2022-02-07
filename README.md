## Descrição do Projeto
- Uma API REST que permite cadastrar clientes do sistema.

## Tecnologias Usadas
- Maven versão 3.8.4.
- Spring Boot 2.6.3
- MapStruct 1.3.1.Final
- Java 11
- Lombok 1.18.22

## Intalação do Projeto

1. Clone o projeto com o git.
    ```
   git clone "repositorio gitHub"
   ```
2. Vá na opção Project from Existing Sources.
![alt text](https://github.com/mateus-asouza/personApi/blob/master/src/main/.github/images/Screenshot_1.png?raw=true)
3. Escolha aoção Maven e clique em finish.
   ![alt text](https://github.com/mateus-asouza/personApi/blob/master/src/main/.github/images/Screenshot_2.png?raw=true)
4. Clique na aba run > Edit Configuration.
   ![alt text](https://github.com/mateus-asouza/personApi/blob/master/src/main/.github/images/Screenshot_3.png?raw=true)
5. Configure um aplication como na imagem e clique em ok.
   ![alt text](https://github.com/mateus-asouza/personApi/blob/master/src/main/.github/images/Screenshot_4.png?raw=true)
6. Execute o comando mcn clean install.
   ![alt text](https://github.com/mateus-asouza/personApi/blob/master/src/main/.github/images/Screenshot_5.png?raw=true)
7. Inicie a aplicação.

## Utilização do lombok

- Esse projeto utiliza a biblioteca do [lombok] para automatizar o processo de desevolvimento.
- Para maiores informações sobre a configuração do lombok acesse https://projectlombok.org/

## Utilização Mockito

- Esse projeto utiliza a biblioteca do Mockito para testes unitários.
- Para maiores informações sobre a
  configurações https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html.

## Utilização MapStruct

- Esse projeto utiliza a biblioteca do MapStruct, que é um gerador de código que simplifica bastante a implementação de
  mapeamentos entre tipos de bean Java com base em uma abordagem de convenção sobre configuração.
- Para maiores informações sobre a configurações https://mapstruct.org/.

## Utilização Spring Boot

- O Spring Boot é uma ferramenta que visa facilitar o processo de configuração e publicação de aplicações que utilizem o
  ecossistema Spring.
- Para maiores informações sobre a configurações https://spring.io/.

## Metodos

- ### POST
    - Request (/api/v1/person)
        - Rquest Body
      ```
        {
      "name": "Fulano",
      "cpf": "643.862.870-00",
      "birthDay": "14/04/1995",
      "phones": [
      {
      "phoneNumber": "(62)999999999"
      }
      ],
      "addresses": [
      {
      "cep": "74375500",
      "street": "Rua das Camélias",
      "number": 0,
      "district":"Parque Oeste",
      "city": "Goiânia",
      "state": "GO"
      }
      ]
      }
      ```
        - Response
       ```
       {
        "message": "Created person with ID 1"
        }
      ```

- ### GET
    - Request (/api/v1/person)
        - Rquest Body

        - Response

```
     [
    {
        "id": 1,
        "name": "Fulano",
        "cpf": "643.862.870-00",
        "birthDay": "14/04/1995",
        "phones": [
            {
                "id": 1,
                "phoneNumber": "(62)999999999"
            }
        ],
        "addresses": [
            {
                "id": 1,
                "cep": "74375500",
                "street": "Rua das Camélias",
                "number": 0,
                "district": "Parque Oeste",
                "city": "Goiânia",
                "state": "GO"
            }
        ]
    }
]
```

- ### GET by ID
    - Request (/api/v1/person/{id})
        - Rquest Body

        - Response

```
{
    "id": 1,
    "name": "Fulano",
    "cpf": "643.862.870-00",
    "birthDay": "14/04/1995",
    "phones": [
        {
            "id": 1,
            "phoneNumber": "(62)999999999"
        }
    ],
    "addresses": [
        {
            "id": 1,
            "cep": "74375500",
            "street": "Rua das Camélias",
            "number": 0,
            "district": "Parque Oeste",
            "city": "Goiânia",
            "state": "GO"
        }
    ]
}

```

- ### PUT
    - Request (/api/v1/person/{id})
        - Rquest Body

```
  {
        "id": 1,
        "name": "Fulano de Tal",
        "cpf": "756.293.000-75",
        "birthDay": "14/04/1995",
        "phones": [
            {
                "id": 1,
                "phoneNumber": "(62)999999999"
            }
        ],
        "addresses": [
            {
                "id": 1,
                "cep": "74375500",
                "street": "Rua das Camélias",
                "number": 0,
                "district": "Parque Oeste",
                "city": "Goiânia",
                "state": "GO"
            }
        ]
    }
```

- Response

```
{
    "message": "Updated person with id 1"
}
```

- ### DELETE
    - Request (/api/v1/person/{id})
        - Rquest Body

        - Response

```
{
    "message": "Deleted person with id 1"
}

```

## Informações Uteis

- @RestController: retorna o objeto e os dados do objeto são gravados diretamente na resposta HTTP como JSON.
- @RequestMapping("/api/v1/people"): mapeia um caminho de requisições.
- @AllArgsConstructor(onConstructor = @__(@Autowired)): Gera um construtor all-args e injeta a anotação @Autowired no
  construtor.
    - @PostMapping: Anotação para mapear solicitações HTTP POST em métodos de manipulador específicos.
    - @RequestBody: O Spring desserializa automaticamente o JSON em um tipo Java, supondo que um apropriado seja
      especificado. Por padrão, o tipo que anotamos com a anotação @RequestBody deve corresponder ao JSON enviado do
      nosso controlador do lado do cliente.
    - @Valid: Irá executar a validação de todas as anotações da classe.
    - @Data: anotação de atalho conveniente que agrupa os recursos de @ToString, @EqualsAndHashCode, @Getter/@Setter e
      @RequiredArgsConstructorjuntos.
  - @Builder: permite que você produza automaticamente o código necessário para que sua classe seja instanciável com código como:
  ```
  Person.builder()
    .name("Adam Savage")
    .city("San Francisco")
    .job("Mythbusters")
    .job("Unchained Reaction")
    .build();
  ```
    - @AllArgsConstructor: Gera um construtor all-args.
    - @NoArgsConstructor: Gera um construtor vazio.
    - @NotEmpty: Um campo restrito com @NotEmpty não deve ser nulo e seu tamanho/comprimento deve ser maior que zero.
    - @NotNull: Não permitirá valores nulos para os campos restritos.
    - @Id: É utilizada para informar ao JPA qual campo/atributo de uma entidade estará relacionado à chave primária da
      respectiva tabela no banco de dados.
    - @GeneratedValue(strategy = GenerationType.IDENTITY): Utilizada para iformartar ao JPA que este campo será do tipo
      autoincremental.
    - @Column(nullable = false): ele aplicará a restrição not null à coluna do banco de dados em particular.
    - @Mapper: marca a interface como interface de mapeamento e permite que o processador MapStruct entre em ação
      durante a compilação.
    - @Service: Marcamos beans com @Service para indicar que eles estão mantendo a lógica de negócios. Além de ser
      utilizada na camada de serviço, não há outro uso especial para esta anotação.
    - @Mock: cria uma implementação simulada para as classes que você precisa.
    - @InjectMock: cria uma instância da classe e injeta as simulações marcadas com as anotações
    - @Test: para marcar um método como um método de teste.
  

## Padronização de branchs

- O padrão convencionado de nomes para a criação de novas branchs é feature/assunto_atividade.
- Novas branchs serão criadas sempre a partir da master.