<%@ include file="templates/header.jspf"%>

    <script>
        function selectPit(isPlayerTurn, playerNumber, pitNumber){
            if(isPlayerTurn == "true"){
                location.href='/pitSelected?playerNumber=' + playerNumber + '&pitNumber=' + pitNumber;
            } else{
                $("#info").text("It is not your turn");
            }
        }
    </script>

    <%@ page import="com.backbase.test.kalah.model.PitType" %>

    <c:set var="numberOfPits" value="${fn:length(kalah.player1.pits)}"/>
    <div>

        <div class="container">

            <label class="text-centered"> Player 1 </label>

            <div class="row">

                <div class="col-lg-3 panel panel-default store">
                    <c:forEach var="i" begin="1" end="${kalah.player1.pits[numberOfPits-1].stones}">
                        <div class="stone"></div>
                    </c:forEach>
                </div>

                <div class="col-lg-6">
                    <div class="row">
                        <c:forEach var="index" begin="0" end="${numberOfPits}">
                            <c:set var="pit" value="${kalah.player1.pits[numberOfPits-index]}" />
                            <c:if test = "${pit.pitType == PitType.HOUSE}">
                                <div class="col-lg-2 panel panel-default pit" onclick="selectPit('${kalah.player1.playerTurn}', 1, '${pit.id}')">
                                    <c:forEach var="i" begin="1" end="${pit.stones}">
                                        <div class="stone"></div>
                                    </c:forEach>
                                    <c:if test="${pit.stones == 0}">
                                        <div>Empty pit</div>
                                    </c:if>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <div class="row">
                        <c:forEach items="${kalah.player2.pits}" var="player2Pits">
                            <c:if test = "${player2Pits.pitType == PitType.HOUSE}">
                                <div class="col-lg-2 panel panel-default pit" onclick="selectPit('${kalah.player2.playerTurn}', 2, '${player2Pits.id}')">
                                    <c:forEach var="i" begin="1" end="${player2Pits.stones}">
                                        <div class="stone"></div>
                                    </c:forEach>
                                    <c:if test="${player2Pits.stones == 0}">
                                        <div>Empty pit</div>
                                    </c:if>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <div class="col-md-3 panel panel-default store">
                    <c:forEach var="i" begin="1" end="${kalah.player2.pits[numberOfPits-1].stones}">
                        <div class="stone"></div>
                    </c:forEach>
                </div>

            </div>

            <label class="text-centered"> Player 2 </label>

        </div>
        <label id="info" class="text-center"></label>
    </div>

<%@ include file="templates/bottom.jspf"%>