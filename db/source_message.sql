CREATE DATABASE  IF NOT EXISTS `source` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `source`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: source
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `message_id` bigint(8) NOT NULL,
  `source_name` varchar(90) NOT NULL,
  `channel` varchar(90) NOT NULL,
  `title` varchar(450) NOT NULL,
  `creator` varchar(90) DEFAULT NULL,
  `link` varchar(900) DEFAULT NULL,
  `description` text,
  `contents` mediumtext,
  `timestamp` varchar(90) DEFAULT NULL,
  `number_of_images` int(11) DEFAULT NULL,
  `pub_date` varchar(450) DEFAULT NULL,
  `day_created` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1488491232891,'Asahi','Top Stories','性的少数者、生きづらさ鮮明　７２％「差別発言聞いた」','Asahi','http://www.asahi.com/articles/ASK2W2JZDK2WUTFK001.html?ref=rss','性的少数者、生きづらさ鮮明　７２％「差別発言聞いた」','　若い世代ほど差別的な発言にさらされていて、親へのカミングアウト率には地域差がある――。日高庸晴・宝塚大教授（社会疫学）の調査で、性的少数者の生きづらさの実態が浮かんだ。回答者は約１万５千人。日高氏によると、性的少数者全般を対象にした調査としては国内最大規模だという。 　調査は昨年７～１０月、ライフネット生命保険の委託で実施された。性的少数者向けのインターネットサイトにバナー広告を掲載するなどして、４７都道府県の１０～９４歳から回答を得た。 　「職場や学校で性的少数者について差別的発言を聞いたことがある」という人は７２％に達した。１０代が７７％▽２０代が７５％▽３０代が７０％▽４０代が６９％▽５０代以上が６４％で、若い世代ほど差別的発言にさらされていた。 　親にカミングアウトしている人…','1488491232891',0,'2017-03-03T04:09:13+09:00','2017-03-02'),(1488491233996,'Asahi','Top Stories','「検診を普及すべき」　子宮頸がんワクチン訴訟で原告側','Asahi','http://www.asahi.com/articles/ASK325WFVK32OIPE021.html?ref=rss','「検診を普及すべき」　子宮頸がんワクチン訴訟で原告側','　子宮頸（けい）がんワクチン接種後の副作用を訴える東海地方の女性１１人が、国や製薬会社に損害賠償を求めた訴訟の口頭弁論が２日、名古屋地裁であった。原告側は「検診は、適切な治療と組み合わせればワクチンより発症を予防できる。検診を普及すべきだ」と訴えた。 　原告側は、発症リスクの高いウイルスの遺伝子型１５種類のうち、ワクチンが対象とするのは２種類で、「ほかのウイルス型の発症を防ぐことは予定していない」と指摘。体調が安定しない原告の１０代女性は、「高校を卒業したが就職先は見つからなかった。不安な日々です」と訴えた。 　被告のＭＳＤ社とグラクソ・スミスクライン社によると、この２種類によるがんの割合は２０～３０代で７０％以上だという。また、検診は「感染自体を発見できず、病変を必ず発見できるとも限らない」と指摘。ワクチンの安全性と有効性は医学会や国際機関で確認されており、「積極勧奨が差し控えられたことで、日本人女性がリスクにさらされている」と訴えた。','1488491233996',0,'2017-03-03T02:10:51+09:00','2017-03-02'),(1488491234570,'Asahi','Top Stories','２警部補を在宅起訴　同僚女性へのわいせつ行為　福岡','Asahi','http://www.asahi.com/articles/ASK325VR0K32TIPE024.html?ref=rss','２警部補を在宅起訴　同僚女性へのわいせつ行為　福岡','　同僚の女性警察官にわいせつな行為をしたとして、書類送検された福岡県警留置管理課の警部補の男２人を、福岡地検は２日、強制わいせつ罪で在宅起訴した。県警は２日、この２人を含む警察官７人を停職などの処分にした。２人は２日付で依願退職した。 　県警は「組織内の処分」として氏名を公表していないが、朝日新聞の取材では在宅起訴されたのは於保（おほ）重信・元警部補（５８）と枝尾光博・元警部補（５７）。 　監察官室によると、２０１５年９月、福岡市の飲食店であった留置管理課員の飲み会で、於保元警部補が女性を羽交い締めにし、枝尾元警部補が覆いかぶさって、２人で３分間にわたって体を無理やり触るなどしたとされる。２人は行為を認めているという。 　２人は別の飲み会で男性課員に…','1488491234570',0,'2017-03-03T00:42:07+09:00','2017-03-02'),(1488491235060,'Asahi','Top Stories','青梅線が一時運転見合わせ　人身事故の影響','Asahi','http://www.asahi.com/articles/ASK327QGZK32UTIL05C.html?ref=rss','青梅線が一時運転見合わせ　人身事故の影響','　【ＪＲ東日本発表】青梅線は、２日午後１０時４７分ごろに牛浜―拝島駅間で起きた人身事故の影響で、青梅―立川駅間の上下線で運転を見合わせていたが、３日午前０時３分ごろに再開した。','1488491235060',0,'2017-03-03T00:22:40+09:00','2017-03-02'),(1488491237317,'Asahi','Top Stories','１億円脱税した疑い、会社社長ら３人逮捕　名古屋地検','Asahi','http://www.asahi.com/articles/ASK323D86K32OIPE007.html?ref=rss','１億円脱税した疑い、会社社長ら３人逮捕　名古屋地検','　不動産売買などの所得約４億３千万円を隠して約１億円を脱税したとして、名古屋地検特捜部は２日、名古屋市東区の不動産売買会社「ジーザス」社長で、韓国籍の黄元圭（ファンウォンギュ）容疑者（５５）ら３人を法人税法違反容疑で逮捕し、発表した。地検は認否を明らかにしていない。 　ほかに逮捕されたのは、経理担当の同社役員で韓国籍の成慶得（ソンキョンドゥク）容疑者（４２）と別会社の役員小林良身容疑者（５２）。特捜部と名古屋国税局はこの日、合同で黄容疑者の自宅や会社など計１６カ所を家宅捜索した。 　特捜部などによると、３人は共謀し、同社所有の賃貸マンションや土地を売却。売却益を除外したり、架空の外注費を計上したりして、２０１５年５月までの１年間で所得約４億２７６０万円を隠し、１億８２０万円を脱税した疑いがある。 　これらの不動産売買で黄容疑者は、宗教法人を介した取引を装っていたとみられている。 　関係者によると、黄容疑者は１４年８月、自身が代表の静岡県伊東市の宗教法人にマンションを「売却」。さらに宗教法人が第三者に「転売」したように見せかけていたとされる。国税局は１５年１０月から強制調査（査察）に着手。黄容疑者は国税局の調べに「宗教法人が売却して得た所得は非課税だ」などと、脱税の認識を否認していたとみられる。 　ただ、宗教法人については税法上、お布施などの非収益事業は非課税だが、不動産売却などの収益事業の所得は課税対象となる。 　登記簿などによると、この宗教法人は黄容疑者が０６年に買収したもので、休眠状態だった。信用調査会社によると、ジーザスは１９９０年１２月の設立で、不動産賃貸のほかに、不動産売買や仲介の業務にも携わる。売上高は１６年５月期で６２００万円。','1488491237317',0,'2017-03-03T00:22:35+09:00','2017-03-02'),(1488491238178,'Asahi','Top Stories','熊本・大分・宮崎などで震度４','Asahi','http://www.asahi.com/articles/ASK327XF1K32TIPE02X.html?ref=rss','熊本・大分・宮崎などで震度４','　２日午後１１時５３分ごろ、日向灘を震源とする地震があり、熊本県阿蘇や大分県南部、宮崎県北部などで震度４を観測した。気象庁によると、震源の深さは約３０キロ、地震の規模を示すマグニチュード（Ｍ）は５・２と推定される。','1488491238178',0,'2017-03-03T00:21:35+09:00','2017-03-02'),(1488491238688,'Asahi','Top Stories','サーバーに不正アクセスした容疑　高校生３人を書類送検','Asahi','http://www.asahi.com/articles/ASK325H60K32TLVB00H.html?ref=rss','サーバーに不正アクセスした容疑　高校生３人を書類送検','　人気のゲーム「マインクラフト」で知り合った熊本県の中学生のＩＤやパスワードを使ってサーバーに接続したなどとして、熊本県警は２日、札幌市などの高校生３人を不正アクセス禁止法違反などの疑いで書類送検し、発表した。 　このゲームは、ブロックを積み上げて建物や家具などをつくる。無料で遊べるが、一定以上の遊び方では、外部サーバーなどの利用のため有料契約が必要。 　発表によると、札幌市の少年（１９）と宮城県富谷市の少年（１５）は２０１５年４月１日、熊本県の中学生のＩＤとパスワードで都内の業者が管理するサーバーに不正にアクセスし、複数の有料契約を結んだ疑いがある。相模原市の少年（１６）はＩＤとパスワードをインターネット電話「スカイプ」上で公開した疑いがある。 　被害者の中学生が有料登録の代行を札幌市の少年らに頼んでＩＤなどを伝えており、少年らが勝手に４６７万円分の契約を結んでいた。少年らは「中学生の頼み方が気にくわなかった」と話しているという。（板倉大地）','1488491238688',0,'2017-03-03T00:19:09+09:00','2017-03-02'),(1488491239613,'Asahi','Top Stories','食パンに異物、６県で販売中止　ＪＲ九州グループ社製','Asahi','http://www.asahi.com/articles/ASK327GVVK32TIPE02V.html?ref=rss','食パンに異物、６県で販売中止　ＪＲ九州グループ社製','　ＪＲ九州グループのパンの製造販売をする「トランドール」は２日、熊本市中央区の「グレンドールゆめタウン大江店」で売った食パン「マイブレッド２枚入り」にストロー状のものが入っていた、と発表した。 　食パン１枚に長さ８センチ、直径１～２ミリのストロー状のものが混入していたと、買った男性から２月２８日に訴えがあった。袋に穴は開いておらず、福岡市東区の千早工場で２月２６日に製造した過程か、店舗で袋詰めした際に混入した可能性があるという。 　同社は、千早工場製の商品を販売する九州・山口の６県計１８店舗で「マイブレッド」（２～６枚入り）の販売を中止。２６日に千早工場で作られたマイブレッドについては、購入店舗で回収・返金する。','1488491239613',0,'2017-03-03T00:10:14+09:00','2017-03-02'),(1488491241860,'Asahi','Top Stories','プールに飛び込み頸髄損傷　小６、課外授業で練習中','Asahi','http://www.asahi.com/articles/ASK3254FHK32PUUB00G.html?ref=rss','プールに飛び込み頸髄損傷　小６、課外授業で練習中','　鳥取県中部の町立小学校で昨夏、６年の女子児童（１２）が水泳の課外授業で教諭の指導の下、プールに飛び込んだ際に頭を強打して頸髄（けいずい）を損傷する事故があった。町教育委員会は２日、調査委員会の初会合を開いた。 　学校の事故報告書などによると、昨年７月１５日午後５時２０分ごろ、女子児童がプールのスタート台（高さ約３６センチ）から、水中にいる児童が持ったフラフープに向かって飛び込み、プールの底に頭を強打。自力で上がれず指導教諭に引き上げられた。事故地点のプールの深さは９０センチだった。両親によると、女子児童は救急搬送され、６日間入院。今も手のしびれを訴え、リハビリを続けているという。 　学習指導要領は、事故防止のため、小学校の水泳の授業では水中からスタートするよう定めている。校長によると、指導教諭はこの点を理解していたが、課外授業だったことや、飛び込みスタートの水泳大会の練習だったことから、飛び込みを練習させたという。 　校長によると、事故前日も、課外授業の水泳練習で飛び込んだ５年の女子児童がプールの底に頭を打った。この時は別の教諭らが指導していたが、気づかず、女子児童が後日、頭の痛みを担任に訴えた。担任が報告せず、校長は１２月になって把握したという。（横山翼）','1488491241860',0,'2017-03-02T23:57:29+09:00','2017-03-02'),(1488491244176,'Asahi','Top Stories','「紹介したバイト行かず」暴行　傷害致死容疑の２人供述','Asahi','http://www.asahi.com/articles/ASK325K71K32PIHB02X.html?ref=rss','「紹介したバイト行かず」暴行　傷害致死容疑の２人供述','　兵庫県西宮市の武庫川河口で２月、同県尼崎市出身の山地直人さん（１８）が遺体で見つかった事件で、傷害致死容疑で逮捕された知人の少年２人（いずれも１８歳）が「（山地さんが）自宅に泊まったのに金を払わず、アルバイトを紹介しても行かないことがあった」と動機について供述していることが県警への取材でわかった。 　捜査１課によると、山地さんは事件前、住む家がなく、知人宅を転々としていた。少年らは宿泊代名目で金を要求していたという。２人は２月４日未明、西宮市鳴尾浜１丁目の岸壁そばで山地さんを暴行し、死なせた疑いがある。 　２人は調べに、ぐったりした山地さんを置いて現場をいったん離れたが、数時間後に戻り、山地さんを消波ブロックの間に運んだ、と説明しているという。 　神戸地検尼崎支部は近く２人を傷害致死の非行内容で家裁に送致する。','1488491244176',0,'2017-03-02T23:56:38+09:00','2017-03-02'),(1488491245254,'Asahi','Top Stories','稀勢の里の新しい化粧まわし、赤富士と鶴の図柄','Asahi','http://www.asahi.com/articles/ASK324R7SK32UTQP00S.html?ref=rss','稀勢の里の新しい化粧まわし、赤富士と鶴の図柄','　新横綱稀勢の里に２日、土俵入りで締める化粧まわし一式が贈られた。１９年ぶりに誕生した日本出身横綱を祝い、赤富士と鶴の図柄。鶴は師匠の田子ノ浦親方の現役時代のしこ名・隆の鶴にもちなんでおり、太刀持ち、露払いの化粧まわしにも鶴が描かれている。 　これまでの土俵入りでは先輩横綱の化粧まわしを借りていたが、春場所に間に合わせるために田子ノ浦部屋の後援会が新調した。また、岐阜県の刀匠が手がけた太刀も贈られた。 　部屋の激励会で約７００人にお披露目した稀勢の里は、「化粧まわし、太刀に恥じぬような成績を残したい」とあいさつした。','1488491245254',1,'2017-03-02T23:06:24+09:00','2017-03-02'),(1488491247584,'Asahi','Top Stories','タカ和田「情報集め」の投球　ロッテ新外国人の実力測る','Asahi','http://www.asahi.com/articles/ASK325Q1CK32TIPE01W.html?ref=rss','タカ和田「情報集め」の投球　ロッテ新外国人の実力測る','　開幕投手に内定しているプロ野球ソフトバンクの和田毅投手が２日、練習試合に先発した。前日に投げる予定だったが雨で流れ、開幕戦（３１日、ヤフオクドーム）で戦うロッテ相手の登板となった。 　ロッテ打線には４番にバラデス、６番にダフィーと新外国人２人が名を連ねた。「どういうスイングをしてくるのか。情報を集めないといけない」。バラデスを三ゴロに仕留めると、ダフィーには「直球は内角にしか投げてない」と実力を測ったが、左越えに豪快なソロ本塁打を浴びた。 　最速１４２キロを計測し、２回１失点の内容を「本塁打は余計でしたけど、対戦したことない打者に投げられたのは収穫。まだ１打席での印象だが、２人とも大振りする打者じゃない」と分析していた。「今日の結果をシーズンの糧にしていければいい」。３６歳は冷静に開幕を見据える。','1488491247584',1,'2017-03-02T21:54:49+09:00','2017-03-02'),(1488491248895,'Asahi','Top Stories','数百円の預金残高から再建、日本一　Ｂリーグの敏腕社長','Asahi','http://www.asahi.com/articles/ASK267JTDK26UTQP01P.html?ref=rss','数百円の預金残高から再建、日本一　Ｂリーグの敏腕社長','　今年１月にあったバスケットボールの全日本総合選手権で初優勝した千葉ジェッツ。船橋市を本拠とする創設６季目の市民球団で、Ｂリーグに統合される前のｂｊリーグとＮＢＬの両方を知る唯一のクラブだ。急速に力をつけたチーム成績だけでなく、草創期に経営危機を救い、売上高や観客動員数の記録を塗り替えてきた島田慎二社長（４６）の経営手腕にも注目が集まっている。 　最初にクラブに招かれた２０１１年の秋、見せられた通帳の預金残高は数百円だった。県内のバスケット好きがクラブを立ち上げ、この年にｂｊリーグに参入したが、経営は素人の集まり。行き詰まった当時のオーナーが頼ったのが、旅行会社などの経営を経てコンサルタントとして活動していた島田さんだった。 　１週間で４０ページほどの再建計画のリポートを作って提出。仕事はそれで終わりのはずだった。しかし、しばらく執行役員として残って再建を手伝うことに。さらに１２年、社長就任も打診された。一度は断ったが、「引き受けてくれないなら会社を清算する」と泣きつかれ、折れた。 　未経験のスポーツビジネスの世…','1488491248895',2,'2017-03-02T21:41:37+09:00','2017-03-02'),(1488491250324,'Asahi','Top Stories','阿部・小笠原組が開幕３連勝　カーリング混合ダブルス','Asahi','http://www.asahi.com/articles/ASK3251BQK32UTQP00Y.html?ref=rss','阿部・小笠原組が開幕３連勝　カーリング混合ダブルス','　カーリング混合ダブルスの日本選手権第２日は２日、北海道北見市で１次リーグがあり、推薦出場の阿部晋也、小笠原歩組が２連勝して、開幕３連勝とした。同じく推薦で出場した谷田康真、小野寺佳歩組は、昨年優勝の蒔苗（まかなえ）匠馬、荒木絵理組を破って３連勝。松村雄太、吉村紗也香組も３連勝した。４人制の日本選手権で男子２位だった札幌、女子４位だった北海道銀行の選手による３組が、協会による推薦枠で出場している。混合ダブルスは２０１８年平昌五輪からの新種目。','1488491250324',2,'2017-03-02T21:05:02+09:00','2017-03-02'),(1488491251707,'Asahi','Top Stories','山中慎介の「進化した左」、相手のガードこじあけた','Asahi','http://www.asahi.com/articles/ASK3235PQK32UTQP006.html?ref=rss','山中慎介の「進化した左」、相手のガードこじあけた','（２日、ＷＢＣバンタム級タイトル戦） 　最後の最後まで、己の武器を信じて拳を振り続けた。「倒れても、起き上がってくるのは想定内だった」 　７回だ。山中の左ストレートが、相手のガードをこじあけて顔面をとらえる。これでもか、これでもかと打ち込む手を緩めない。この日５回目のダウンを奪うと、レフェリーが試合を止めた。 　過去２８戦無敗でＫＯ率が６割を超える山中の最も得意とするパンチは、左ストレートだ。２回目の防衛戦から５試合連続ＫＯ勝ちしたことで、「相手が左をもらわないよう、研究してくるようになった」と帝拳ジムの浜田剛史代表は言う。ここ５試合のうち３試合は判定までもつれた。 　１２回目の防衛戦に向けて山中が目指したのは、相手の研究をもはねのける「進化した左」（大和トレーナー）だった。グローブをつけない素手に近い状態でミットを打つ練習を新たに採り入れ、どの角度で打ち込めば、より拳に力が伝わるのかを体に覚え込ませた。 　内山高志も、長谷川穂積も届かなかった１２連続防衛。具志堅用高さんが１９８０年に達成して以来、日本ジム所属の男子選手としては３７年ぶり２人目の快挙だ。それでも、山中はリング上で淡々と言った。「記録は意識しない。みなさんが期待して、楽しんでもらえれば。結果はついてくる」。盛り上がる周囲をよそに、平常心を貫くことができれば、目前に迫った大記録にも手が届く。（清水寿之）','1488491251707',2,'2017-03-02T21:03:23+09:00','2017-03-02'),(1488491252829,'Asahi','Top Stories','東芝機械の株式を１５３億円で売却　東芝、資本増強で','Asahi','http://www.asahi.com/articles/ASK325QFRK32ULFA01Y.html?ref=rss','東芝機械の株式を１５３億円で売却　東芝、資本増強で','　東芝は２日、グループの工作機械メーカー東芝機械（東京都千代田区）の株式を売却すると発表した。東芝は米原子力事業を巡る巨額損失で経営難に陥っており、資本増強策の一環。保有する２０・１％のうち１８・１％分を、東芝機械の自社株買いに応じる形で約１５３億円で売却する。 　事業や技術連携のため２％は持ち続けるが、東芝の持ち分法適用会社からは外れることになる。約５５億円の売却益は２０１７年３月期決算の業績見通しに反映済みという。 　東芝は、米国での原子力事業を巡って７千億円超の損失を昨年４～１２月期決算に計上し、今月末時点で自己資本がマイナスになる債務超過に陥る見通し。上場しているほかのグループ６社の株式についても売却を検討するなど、様々な資本増強策を進めている。','1488491252829',0,'2017-03-02T21:11:03+09:00','2017-03-02'),(1488491255086,'Asahi','Top Stories','お祭り屋台のお菓子、手軽にクッキング　クラシエフーズ','Asahi','http://www.asahi.com/articles/ASK2R5FGSK2RULFA02N.html?ref=rss','お祭り屋台のお菓子、手軽にクッキング　クラシエフーズ','　クラシエフーズは３月６日、「ポッピンクッキン　たのしいおまつりやさん」を発売する。付属の水と粉を混ぜ合わせれば、屋台のメニューに似せたお菓子を作ることが出来る。作れるのはチョコバナナやとうもろこしなど４種類。２４ｇ入りで税抜き２００円。','1488491255086',1,'2017-03-02T16:04:50+09:00','2017-03-02'),(1488491256533,'Asahi','Top Stories','日経平均、３日続けて上昇　終値、２カ月ぶりの高値','Asahi','http://www.asahi.com/articles/ASK324T55K32ULFA016.html?ref=rss','日経平均、３日続けて上昇　終値、２カ月ぶりの高値','　２日の東京株式市場は、日経平均株価が３日続けて上昇した。終値は、前日より１７１円２６銭（０・８８％）高い１万９５６４円８０銭で、１月４日以来、約２カ月ぶりの高値をつけた。取引時間中には一時、今年の最高値をつける場面もあった。東京証券取引所第１部全体の値動きを示すＴＯＰＩＸ（東証株価指数）の終値は、同１１・６０ポイント（０・７５％）高い１５６４・６９。出来高は２２億１千万株。 　前日の米国市場でダウ工業株平均が過去最高値を更新した流れを引き継ぎ、日経平均は一時、２７０円超上昇した。ほぼ全面高の展開だった。特にトランプ米大統領によるインフラ投資拡大への期待から、建設機械株の上昇が目立った。','1488491256533',0,'2017-03-02T15:22:57+09:00','2017-03-02'),(1488491257113,'Asahi','Top Stories','サバ養殖、ネットで資金集め　業者と小浜市が新事業','Asahi','http://www.asahi.com/articles/ASK3145W3K31PLFA007.html?ref=rss','サバ養殖、ネットで資金集め　業者と小浜市が新事業','　ネットを通じて資金を集めるクラウドファンディングの手法を使ってサバを養殖する新たな取り組みが始まる。大阪や東京などでサバ料理専門店を展開する鯖（さば）や（大阪府豊中市）が、かつて京都までサバを運んだ「鯖街道」の起点・福井県小浜市と組んで、地元に利益が出る仕組みを目指している。 　集めたお金は小浜市で養殖したマサバの購入や、サバを出す新店舗の設備などに使う。養殖のため寄生虫がつきにくいという。鯖やはＪＲ西日本などと組んで養殖事業を手がけ、「お嬢サバ」と名付けたサバを出荷している。今回のサバは「鯖街道よっぱらいサバ」と名づけ、初出荷は来年の予定だ。 　地元にお金を落とすことも事業の目的だ。小浜で水揚げされた稚魚を通常なら１キロあたり約５０円のものを約１千円で仕入れる。福井のコメを使った酒かすをえさに混ぜ、育ったサバは通常１キロ約１５００円のところを約２千円で買い取る。右田孝宣・鯖や社長は「漁業者らの所得が上がり、お店も繁盛するようにしたい」と話す。 　１口２万５千円で、募集は８日から。「いいさば」とかけて１億１３８０万円を集める目標だ。出資者は新店舗の売り上げの一部が５年間配当され、１口につき５千円相当の食事券がつく。ただし、出資した元本が戻る保証はない。 　小浜市のサバの漁獲量は１９７４年の３５８０トンから、２０１４年は１トンまで減少。昨年から市もサバの養殖を支援している。１日から高島屋大阪店で始まった福井県の物産展では、松崎晃治市長が養殖サバを使ったすしなどをアピールした。松崎市長は「漁業者らの所得も増える良い循環にしたい」と語る。（岩沢志気）','1488491257113',1,'2017-03-02T14:22:48+09:00','2017-03-02'),(1488491259667,'Asahi','Top Stories','日経平均、今年の最高値更新　ほぼ全面高','Asahi','http://www.asahi.com/articles/ASK322T18K32ULFA002.html?ref=rss','日経平均、今年の最高値更新　ほぼ全面高','　米国株急上昇の流れを引き継ぎ、２日の東京株式市場は、ほぼ全面高となっている。日経平均株価は、取引時間中としては一時、今年の最高値を更新し、２７０円超値上がりした。午後１時時点は、前日終値より１８１円８５銭高い１万９５７５円３９銭。東京証券取引所第１部全体の値動きを示すＴＯＰＩＸ（東証株価指数）は同１３・１６ポイント高い１５６６・２５。午前の終値は、日経平均が同２５０円０８銭（１・２９％）高い１万９６４３円６２銭。ＴＯＰＩＸは同１８・４６ポイント（１・１９％）高い１５７１・５５。 　米国経済の拡大が日本に波及する期待感から、東京市場では朝方から幅広い銘柄で買い注文が膨らんだ。トランプ米大統領によるインフラ投資拡大への期待感も強く、建設機械株が買われている。','1488491259667',0,'2017-03-02T13:13:12+09:00','2017-03-02'),(1488491261934,'Asahi','Top Stories','世論調査―質問と回答〈福島県民調査〉','Asahi','http://www.asahi.com/articles/ASK330084K31UGTB01G.html?ref=rss','世論調査―質問と回答〈福島県民調査〉','（数字は％。小数点以下は四捨五入。質問文と回答は一部省略。丸カッコ内の数字は、２月１８、１９日の全国定例調査の結果） ◆福島県の内堀雅雄知事を支持しますか。支持しませんか。 　支持する７３ 　支持しない８ ◆いま停止している原子力発電所の運転を再開することに、賛成ですか。反対ですか。 　賛成９（２９） 　反対８２（５７） ◆福島第一原子力発電所の事故に対する、これまでの政府の対応を評価しますか。評価しませんか。 　評価する２１ 　評価しない６０ ◆福島第一原発の汚染水問題について、国や東京電力の対応を評価しますか。評価しませんか。 　評価する１４ 　評価しない７１ ◆国や自治体が行う除染作業についてうかがいます。これまでの除染作業をどの程度評価しますか。（選択肢から一つ選ぶ＝択一） 　大いに評価する３ 　ある程度評価する４８ 　あまり評価しない３９ 　まったく評価しない７ ◆東京電力の原子力発電所に関する情報公開についてうかがいます。東京電力の情報公開に対する姿勢を、どの程度評価しますか。（択一） 　大いに評価する２ 　ある程度評価する１６ 　あまり評価しない５７ 　まったく評価しない２４ ◆東京電力・福島第一原発の廃炉や賠償などへの費用は、２０兆円を超すまでふくらんでいます。その費用の一部について、政府は、電気料金へのさらなる上乗せなど、国民負担を増やしてまかなう方針です。この方針に、納得できますか。納得できませんか。 　納得できる１９（３０） 　納得できない７６（６０） ◆政府は今年４月１日までに、帰還困難区域を除き、ほぼすべての避難指示を解除する方針です。この避難指示を解除する時期についてどう思いますか。（択一） 　早すぎる１９ 　遅すぎる９ 　妥当だ４０ 　そもそも解除すべきでない２２ ◆帰還困難区域について、政府は、役場や駅のまわりなどの拠点にしぼって除染をし、２０２２年をめどに、その拠点の避難指示を解除して、人が住めるようにする方針です。この方針に賛成ですか。反対ですか。 　賛成４３ 　反対４２ ◆福島第一原発事故のあと、政府からの避難指示がなくても自主的に避難した人たちに対し、福島県は、住居を無料で提供する支援を３月までで終える方針です。こうした方針は、妥当だと思いますか。妥当でないと思いますか。 　妥当だ５５ 　妥当でない３５ ◆東日本大震災や原発事故から６年がたち、福島の復興への道すじが、どの程度ついたと思いますか。（択一） 　大いについた１ 　ある程度ついた４０ 　あまりついていない４７ 　まったくついていない９ ◆福島県全体で、元のような暮らしができるのは、今からどのくらい先になると思いますか。（択一） 　５年ぐらい７ 　１０年ぐらい１６ 　２０年ぐらい２１ 　２０年より先５０ ◆福島第一原発の事故による放射性物質があなたやご家族に与える影響について、どの程度不安を感じていますか。（択一） 　大いに感じている１９ 　ある程度感じている４４ 　あまり感じていない２８ 　まったく感じていない７ ◆国民の間で福島第一原発事故の被災者への関心が薄れ、風化しつつあると思いますか。そうは思いませんか。 　風化しつつある７４（６８） 　そうは思わない２１（２６） ◆福島第一原発事故のあと、福島県民であることで、差別されていると感じることがありますか。ありませんか。 　ある３０ 　ない６６ 　　　　　◇ 　〈調査方法〉　２月２５、２６の両日、コンピューターで無作為に作成した固定電話番号に調査員が電話をかけるＲＤＤ方式で、福島県内の有権者を対象に調査した（避難指示区域など一部地域を除く）。有権者がいる世帯と判明した番号は１７３９件、有効回答は９３４人。回答率は５４％。','1488491261934',1,'2017-03-03T03:24:31+09:00','2017-03-02'),(1488491264398,'Asahi','Top Stories','元の暮らしまで「２０年超」５割　福島県民に世論調査','Asahi','http://www.asahi.com/articles/ASK2W4JDWK2WUZPS001.html?ref=rss','元の暮らしまで「２０年超」５割　福島県民に世論調査','　東日本大震災と東京電力福島第一原発事故から６年になるのを前に、朝日新聞社と福島放送は共同で、福島県民を対象に世論調査（電話）を行った。県全体で、元のような暮らしができるのは、今からどのくらい先になると思うかを聞くと、半数が「２０年より先」と答えた。 　調査は２月２５、２６日に実施した。事故の半年後に初めて行い、今回が７回目。元の暮らしができる見通しは「２０年より先」５０％、「２０年ぐらい」２１％、「１０年ぐらい」１６％、「５年ぐらい」７％の順だった。 　今回から１８歳、１９歳が調査対象に加わり、単純比較はできないが、２０１３年調査では「２０年より先」は６０％。減少はしたが、依然前途が見いだせない心境がうかがえる。復興への道筋についても、「あまり」４７％、「全く」９％を合わせた「ついていない」が過半数を占めた。 　また、福島県民であることで差別されていると感じることがあるかは、３０％が「ある」と答えた。原発事故の被災者への関心が薄れ、風化しつつあると思うか、の問いには「風化しつつある」が７４％に達した。 　一方、原発事故に対する政府対応には「評価する」２１％、「評価しない」６０％。東電の情報公開に対する姿勢には「あまり」「全く」を合わせた「評価しない」が８１％にのぼり、「大いに」と「ある程度」を合わせた「評価する」１８％を大きく上回った。 　政府は総額２１・５兆円に増えた原発事故の対応費について、電気料金の上乗せなど国民負担を増やして賄う方針だ。この事実上の東電救済策に対し、７６％が「納得できない」と答えた。２月の全国定例調査で同じ質問をしたところ、「納得できない」は６０％で、福島の方が多かった。 　原発の再稼働の賛否は福島では「反対」が８２％、「賛成」は９％にとどまった。２月の全国調査では「反対」５７％だった。こちらも福島の方が反対が強かった。','1488491264398',4,'2017-03-03T03:05:44+09:00','2017-03-02'),(1488491266005,'Asahi','Top Stories','築地「健康に影響ない」　小池知事、土壌汚染巡り答弁','Asahi','http://www.asahi.com/articles/ASK3256Q4K32UTIL027.html?ref=rss','築地「健康に影響ない」　小池知事、土壌汚染巡り答弁','　東京都の築地市場で土壌汚染の可能性を都が指摘していた問題について、小池百合子知事は２日の都議会一般質問で「人の健康に影響を与えることはないと考えている」と述べた。 　自民の川松真一朗都議の質問に答えた。小池氏は「コンクリートやアスファルトで覆われており、土壌汚染対策法などの法令上の問題もない」とも述べた。 　一方、小池氏の見方について、自民会派内では「（汚染問題で揺れる）豊洲市場もコンクリートで覆われており、築地と同じだ」との議論があった。築地と豊洲の安全基準が異なるようにもとれ、高木啓幹事長は２日、報道陣に対し、「豊洲では（有害物質が検出された）地下水の使用は全く想定されていない。豊洲についても科学的知見をベースに判断するべきだ」と述べた。 　豊洲市場の安全性を検証している都の専門家会議の平田健正座長は、豊洲も築地と同じく、市場の敷地がコンクリートで覆われているなどの理由で「科学的には安全」としている。一方で、「科学的な安全と、人の心の安心は違う。（現状では）一般の消費者が納得してくれない面はある」とも認める。 　小池氏は先月２８日、報道陣に…','1488491266005',0,'2017-03-02T23:37:15+09:00','2017-03-02'),(1488491266909,'Asahi','Top Stories','首相夫人の位置づけは？　昭恵氏の言動、国会で論点に','Asahi','http://www.asahi.com/articles/ASK325F94K32UTIL02J.html?ref=rss','首相夫人の位置づけは？　昭恵氏の言動、国会で論点に','　大阪市の学校法人「森友学園」への国有地売却問題で、安倍晋三首相の妻昭恵氏の言動が国会で大きな論点になっている。「家庭内野党」を自任する発言で話題になったこともある昭恵氏。国内外の要人と会い、世間の注目を浴びることも多い首相夫人（ファーストレディー）は、私人と言い切れるのか。 　「妻は私人なんです。まるで犯罪者扱いするのは不愉快ですよ」 　安倍首相は１日、参院予算委員会でこう発言した。森友学園が開校予定の小学校の名誉校長になっていた昭恵氏について、共産党の小池晃氏に学園理事長との詳しい関係を質問されての反論だった。２日の同委員会でも自由党の山本太郎氏の質問に「確かに妻は総理夫人というふうに呼ばれる」としつつ「公人ではない」と答弁した。 　野党側は学園が運営する幼稚園の教育方針も問題視。昭恵氏が講演や小学校のホームページで学園の教育方針を称賛していたなどとして「総理夫妻の政治的、道義的責任は免れない」と追及している。 　海外メディアの関心も高く「安倍晋三と妻が、超国家主義の学校との関係を巡り追い詰められている」（英紙ガーディアン）、「日本のファーストレディー、学校の名誉職を辞任」（米紙ワシントン・ポスト）などと報道。この問題を巡る民進党の会見には、米英や中国のメディアも参加した。 　首相夫人は、政府内でどう位置づけられるのか。 　内閣府によると、第１次安倍政…','1488491266909',2,'2017-03-02T22:41:59+09:00','2017-03-02'),(1488491268261,'Asahi','Top Stories','「共謀罪」条文に「テロ」追加検討　政府、自公要求受け','Asahi','http://www.asahi.com/articles/ASK324Q46K32UTIL01F.html?ref=rss','「共謀罪」条文に「テロ」追加検討　政府、自公要求受け','　犯罪を計画段階で処罰する「共謀罪」の趣旨を盛り込んだ組織的犯罪処罰法改正案について、政府は条文に「テロ」の文言を加える検討を始めた。自民、公明両党からの修正要求を受けたもの。政府はこれまで「テロ等準備罪」という呼称を使ってきたが、条文に「テロ」の文言がなく、批判が出ていた。 　政府は早ければ１０日に法案を閣議決定する予定だったが、与党の審査に時間がかかるため、ずれ込む見通しだ。 　政府は２０２０年の東京五輪・パラリンピックに向けたテロ対策を強調して世論の理解を得ようと、「共謀罪」ではなく「テロ等準備罪」と呼び、閣僚の記者会見や国会答弁で使ってきた。しかし、自公両党に示された法案では、正式な罪名は「実行準備行為を伴う組織的犯罪集団による重大犯罪遂行の計画」の罪で、条文に「テロ」の文言は入っていなかった。 　２日の自民党の法務部会では、…','1488491268261',0,'2017-03-02T20:59:05+09:00','2017-03-02'),(1488491270513,'Asahi','Top Stories','中国、韓国への団体旅行禁止　ＴＨＡＡＤ計画に報復か','Asahi','http://www.asahi.com/articles/ASK3304H7K32UHBI02W.html?ref=rss','中国、韓国への団体旅行禁止　ＴＨＡＡＤ計画に報復か','　中国の国家旅遊局が２日、北京市内の旅行会社に３月１５日以降の韓国への団体旅行を中止するよう口頭で指示を出していたことが旅行業界関係者の話で分かった。中国は、韓国が配備を計画している米軍の高高度迎撃ミサイルシステム（ＴＨＡＡＤ〈サード〉）に強く反対しており、韓国のロッテグループが土地提供に同意したことへの報復措置とみられる。 　韓国には多くの中国人観光客が訪れており、旅行業界や小売業界に大きな打撃となるのは必至。中韓関係はさらに険悪化しそうだ。 　中国国内では、ロッテグループが土地提供に同意した先月２７日以降、中国メディアのロッテ批判が続いている。共産党機関紙・人民日報系の国際紙「環球時報」は２８日の社説で「頭から血を流させるだけでなく、内臓を傷つけろ」と訴え、旅行業から韓流ドラマ、サムスンの携帯電話や現代自動車まで「全面制裁」を主張していた。 　中国メディアの批判の背景には、配備に強く反対する政府の姿勢がある。外務省の耿爽副報道局長は２日、「外国企業の中国での成否は最終的に中国の市場と消費者が決めることだ」と答えた。（北京＝延与光貞）','1488491270513',0,'2017-03-03T05:21:16+09:00','2017-03-02'),(1488491271309,'Asahi','Top Stories','ムバラク氏の無罪確定、デモ隊への発砲命令　エジプト','Asahi','http://www.asahi.com/articles/ASK3304Q9K32UHBI02Y.html?ref=rss','ムバラク氏の無罪確定、デモ隊への発砲命令　エジプト','　エジプトの破棄院（最高裁に相当）は２日、２０１１年の民主化運動「アラブの春」でデモ隊に発砲を命じたとして殺人罪に問われたホスニ・ムバラク元大統領（８８）に対する２度目のやり直し裁判で無罪を言い渡した。ムバラク氏の無罪が確定した。 　ムバラク氏は１１年に起きた反政府デモで、治安部隊にデモ隊への発砲を命じて２００人以上が死亡したとして殺人罪に問われた。 　１２年６月、一審で刑事裁判所は終身刑（求刑死刑）を言い渡したが、１３年に破棄院は刑事裁での審理やり直しを決定。１４年１１月に刑事裁は「罪には問えない」として審理を無効とし、事実上の無罪を言い渡したが、検察が上訴。破棄院が１５年６月に上訴審でやり直し裁判をすることを決めていた。 　同様に殺人罪に問われた元内務相ら他の被告もすでに無罪が確定している。 　無罪確定に対する反発が予想されるが、現シーシ政権は、デモを厳しく厳しく規制しており、大きな混乱にはならないとみられる。（翁長忠雄）','1488491271309',0,'2017-03-03T02:06:21+09:00','2017-03-02'),(1488491271812,'Asahi','Top Stories','正男氏殺害、全容解明遠のく　北朝鮮籍容疑者は不起訴へ','Asahi','http://www.asahi.com/articles/ASK32633WK32UHBI025.html?ref=rss','正男氏殺害、全容解明遠のく　北朝鮮籍容疑者は不起訴へ','　北朝鮮の金正恩（キムジョンウン）・朝鮮労働党委員長の異母兄、金正男（キムジョンナム）氏が殺害された事件で、マレーシアの司法当局は２日、北朝鮮国籍のリ・ジョンチョル容疑者（４６）を不起訴処分とする方針を固めた。３日にも北朝鮮に強制送還する。捜査対象の北朝鮮人で唯一の逮捕者だった容疑者の送還で、事件の全容解明は遠のく。 　「（リ容疑者を）起訴するだけの証拠がそろっていない」。アパンディ司法長官は２日、朝日新聞にこう話し、勾留期限の３日に不起訴処分にする方針を明らかにした。さらに、リ容疑者の労働許可証は先月初旬で期限が切れていたことも判明。リ容疑者は３日に入国管理当局に移され、強制送還される見通しだという。 　今回の事件で、捜査当局が関与を疑う容疑者や重要参考人は計１０人。ベトナム人とインドネシア人の実行犯の女２人以外の８人は北朝鮮人だが、逮捕できた北朝鮮人はリ容疑者のみだ。リ容疑者の役回りの解明は、全容把握への数少ない糸口だった。 　事件では、クアラルンプール国際空港で実行犯の女２人が正男氏に猛毒「ＶＸ」を塗りつけて殺害した。その様子を指示役の北朝鮮人の男４人が間近で見届けたとされる。 　地元メディアによると、事件当日に指示役の男らが使った車両の名義からリ容疑者の名前が浮かんだ。警察は事件発生から４日後の２月１７日、リ容疑者を事件に関与した疑いで逮捕した。ところが、リ容疑者は「車は以前に紛失したものだ」と関与を否定。空港の監視カメラにリ容疑者は映っていなかった。 　また、リ容疑者は「実行犯の女…','1488491271812',2,'2017-03-03T01:51:40+09:00','2017-03-02'),(1488491272764,'Asahi','Top Stories','北朝鮮人のビザなし渡航廃止　マレーシア、事実上の報復','Asahi','http://www.asahi.com/articles/ASK325D1CK32UHBI01B.html?ref=rss','北朝鮮人のビザなし渡航廃止　マレーシア、事実上の報復','　マレーシアのザヒド副首相兼内相は２日、北朝鮮国民に対するビザ（査証）なし渡航制度を６日から廃止する方針を明らかにした。ビザなし渡航は両国の友好の象徴だったが、事件をめぐってマレーシア警察の捜査を批判する北朝鮮に事実上の報復措置を発動する。 　北朝鮮は警察の捜査を「信用できない」などと批判。警察が重要参考人としている在マレーシア北朝鮮大使館の２等書記官に対する警察の面会要請に応じないことなどから、ナジブ政権が対応を検討していた。 　マレーシアは２００９年に、北朝鮮との間でビザなし渡航制度を始めた。北朝鮮人にとってビザなしで渡航できる数少ない国の一つだが、「工作活動の拠点になりかねない」（外務省関係者）との懸念が出ていた。政府はビザ取得を義務づけて、北朝鮮の入国者のチェックを強める考えだ。 　「報復措置」でビザなし渡航の廃止がまず選ばれたのは、経済的影響が少ないことも背景にある。１１年に北朝鮮国営の高麗航空が平壌とクアラルンプール間で直行便を就航させたが、国連の対北朝鮮制裁決議で１４年に運航を中断。マレーシア政府によると、北朝鮮からの旅行者数は昨年、７３６人にとどまっていた。（クアラルンプール＝都留悦史）','1488491272764',1,'2017-03-02T21:39:50+09:00','2017-03-02'),(1488491273739,'Asahi','Top Stories','ベトナムで無償手術２５年、医師の思い　「絆深まれば」','Asahi','http://www.asahi.com/articles/ASK315WBSK31UTIL039.html?ref=rss','ベトナムで無償手術２５年、医師の思い　「絆深まれば」','　ベトナムを訪問中の天皇、皇后両陛下は２日、ハノイで大使夫妻主催の歓迎会に出席する。歓迎会では、ベトナムで口に障害がある子らに無償で手術を続けてきた日本口唇口蓋裂（こうがいれつ）協会の常務理事・夏目長門さん（６０）と対面する。夏目さんは両陛下の訪問で「両国の絆がさらに強まれば」と期待している。 　この協会は夏目さんを中心に１９９２年に発足。インドネシアやモンゴル、ミャンマーなどに診療隊を派遣し、生まれつき唇や上あごがうまく形づくられない「口唇口蓋裂」の子らに無償で手術や診療を施してきた。 　ベトナムへの思い入れは強い。協会発足から２５年で日本からベトナムへの診療隊の派遣は８０回以上となり、派遣人数は延べ１千人以上にのぼる。自身も８０回以上訪れ、同国は「第二のふるさと」と感じている。 　手術をした子どもが住む寺を訪…','1488491273739',2,'2017-03-02T21:11:39+09:00','2017-03-02'),(1488491274814,'Asahi','Top Stories','井頭愛海、さくらと一緒に成長中　「べっぴんさん」娘役','Asahi','http://www.asahi.com/articles/ASK2X43SMK2XPTFC008.html?ref=rss','井頭愛海、さくらと一緒に成長中　「べっぴんさん」娘役','　母親に反発するばかりの少女から会社を支えようと張り切る若手社員へ。ＮＨＫの連続テレビ小説「べっぴんさん」で主人公の一人娘さくら役を演じる井頭愛海（いがしらまなみ）。１５歳のいま、役と一緒にのびやかに成長中だ。 　見た目はもちろん、口調や目線まで、主人公すみれ役の芳根（よしね）京子に似ていてドキリとする。「あえて似せようとは思っていないので、演じるうちに雰囲気が近づいているならうれしい」 　撮影現場でも、髪の質が一緒だと発見したり、食事のあとパンくずを付けたまま取材を受けて、メイクさんに「お母さんと一緒やん」と言われたり。ほおをぶたれた初の共演シーンから母娘がだんだん打ち解けたように、芳根との距離も縮まった。「台本と現実の同時進行で役をつくっていけた」 　いま高校１年。学校で「あ、芳…','1488491274814',3,'2017-03-02T16:34:53+09:00','2017-03-02'),(1488491277848,'Asahi','Top Stories','舒明天皇の初葬地か、蘇我蝦夷の大陵か　小山田古墳','Asahi','http://www.asahi.com/articles/ASK2W46V9K2WPTFC009.html?ref=rss','舒明天皇の初葬地か、蘇我蝦夷の大陵か　小山田古墳','　飛鳥時代で最大級の方形の古墳（方墳）の可能性が高まった奈良県明日香村の小山田（こやまだ）古墳。そこに眠っていたのは、新しい国づくりを目指した舒明（じょめい）天皇（５９３～６４１）だったのか。天皇をしのぐ権勢を誇ったとされる豪族の蘇我蝦夷（そがのえみし）だったのか。なぜ、古墳は短期間で壊されたのか。古代史の謎が深まってきた。 　近畿の天皇や豪族の墓の形は、飛鳥時代を通じて変化する。３世紀中ごろの古墳時代初めから続いた前方後円墳は６世紀末に終わりを告げ、方墳に。７世紀中ごろからは天皇墓に八角形墳が採用される。その八角形墳の始まりが最古の国家寺院、百済大寺（くだらのおおでら）を建て、遣唐使を初めて派遣した舒明天皇の陵墓とされる段ノ塚古墳（奈良県桜井市）だ。 　舒明天皇は６２９年、７世紀前半に厩戸王（うまやとおう＝聖徳太子）や蘇我馬子（うまこ）と政治を進めた推古（すいこ）天皇の死後に即位。馬子の子、蝦夷ら蘇我氏が権力を握るなか、飛鳥の中心から離れた地に百済宮（くだらのみや）や百済大寺を築く。蘇我氏とは距離を置き、天皇中心の中央集権国家づくりを目指したとの見方もある。 　近畿で最大級の方墳は聖徳太子の父、用明（ようめい）天皇の陵墓とされる大阪府太子町の春日向山古墳（東西６６メートル、南北６０メートル）や、推古天皇陵とされる山田高塚古墳（東西６６メートル、南北５８メートル）だが、一辺７０メートルの小山田古墳の規模はこれらを上回る。木下正史・東京学芸大名誉教授（考古学）は「これだけの規模は天皇の墓としか考えられない」と述べ、舒明天皇の墓との見方を示す。 　「日本書紀」は、舒明天皇の遺…','1488491277848',2,'2017-03-02T08:58:52+09:00','2017-03-02'),(1488491280577,'Asahi','Top Stories','世界の半分しか見えぬ男好演　池松壮亮「純粋さを前に」','Asahi','http://www.asahi.com/articles/ASK2W035JK2VPTFC00R.html?ref=rss','世界の半分しか見えぬ男好演　池松壮亮「純粋さを前に」','■ベルリン国際映画祭に招待された俳優・池松壮亮さん（２６） 　極寒のベルリンの空にふわりと浮かんだ気球から、石井裕也監督と２人並んで夕暮れの街を眺めていた。 　石井監督の「映画　夜空はいつでも最高密度の青色だ」（５月公開）に出演し、ドイツのベルリン国際映画祭に招待された。会場近くにあった観光用の気球が気になっていた池松自身のリクエストで、この取材場所になった。 　映画祭では何度か舞台に立ち、観客の感想をきいた。「映画が表現としてまだ成立していると感じられて楽しかったです」 　石井監督が原作である最果（さいはて）タヒの詩集を読んだときに思い浮かべた、「世界の半分しか見ることを許されない男」慎二を池松が演じる。慎二は東京の建設現場で日銭を稼ぎながら、古アパートで一人暮らしをしている。左目がほとんど見えない。その障害を、何が正しいのかわからないことだらけで見通せない、いまの世の中の暗喩として役と絡めた。 　「難しくて自分に演じられるのか考えましたが、純粋なものを前に前に出していけば、慎二が見えてくると思いました」 　慎二はガールズバーでアルバイ…','1488491280577',5,'2017-03-01T14:39:00+09:00','2017-03-02'),(1488491284381,'Asahi','Top Stories','８０ブランド、新作披露　秋冬パリ・コレクションが開幕','Asahi','http://www.asahi.com/articles/ASK2W2GMZK2WUCVL001.html?ref=rss','８０ブランド、新作披露　秋冬パリ・コレクションが開幕','　２０１７年秋冬パリ・コレクションが２８日、開幕した。７日までにパリ市内各所で約８０のブランドが、新作のファッションを発表する。 　初日のウクライナのブランド「パスカル」は、植物を置いた会場で、レースや花飾りを多用したフェミニンなドレスを見せた。 　先行したニューヨークやミラノでは、米トランプ政権の新政策や世界に広がる排外主義への反発として、性別や宗教、民族などの垣根を越えた自由を表現するブランドが目立った。とりわけ多くの国籍のデザイナーが競うパリで、そうした動きがどう表現されるか注目される。日本からはイッセイミヤケやジュンコ・シマダ、若手のアンリアレイジなど約１０ブランドが正式日程で参加。他に東京コレクションで活躍していたビューティフルピープルなども、初めてパリで作品を披露する。（パリ＝編集委員・高橋牧子）','1488491284381',7,'2017-03-01T03:32:45+09:00','2017-03-02'),(1488491286936,'Asahi','Top Stories','アトピー新治療薬、かゆみ抑える効果を確認　京大など','Asahi','http://www.asahi.com/articles/ASK2X3WFWK2XPLBJ001.html?ref=rss','アトピー新治療薬、かゆみ抑える効果を確認　京大など','　日本で開発されたアトピー性皮膚炎の新しい治療薬について、かゆみを抑える効果が、日米欧での臨床試験（治験）で確認された。京都大の椛島健治教授（皮膚科学）らの国際研究グループが２日、米医学誌ニューイングランド・ジャーナル・オブ・メディシンに発表した。 　新しい薬は中外製薬が開発した「ネモリズマブ」で、かゆみにかかわる生理活性物質「インターロイキン３１」の働きを妨げる。日米欧５カ国の７医療機関で、中程度から重度のアトピー性皮膚炎の約１４０人に月１回注射し、３カ月後にかゆみや皮膚の状態などを調べた。 　その結果、患者の６割でかゆみの程度が注射前と比べ、５０％以上改善していた。注射の１週間後、寝付くまでの時間が２０分短縮され、３週間後には安眠している時間が薬を使わない患者と比べて４０～５０分以上長くなっていた。かゆみが減り熟睡につながったとみられる。重い副作用は確認されなかったという。 　椛島教授は「アトピー性皮膚炎の治療は１０年以上進歩がなく、かゆみを抑える薬もなかった。２～３年後には患者の手に届くようにしたい」と話している。（西川迅）','1488491286936',0,'2017-03-03T05:09:50+09:00','2017-03-02'),(1488491287580,'Asahi','Top Stories','ＤＡＺＮトップが謝罪、契約者に補償　Ｊリーグ配信障害','Asahi','http://www.asahi.com/articles/ASK325J94K32UTQP018.html?ref=rss','ＤＡＺＮトップが謝罪、契約者に補償　Ｊリーグ配信障害','　サッカーＪリーグのインターネット配信を始めた「ＤＡＺＮ（ダ・ゾーン）」で開幕戦の映像が見られなかった問題で、同社のジェームズ・ラシュトンＣＥＯが２日、記者会見を開いて謝罪した。開幕戦のあった先月２５、２６日に視聴しようとした契約者に、２週間の無料利用期間提供などで補償する。 　同社によると、ハイライトなどを作るため、試合映像を自動編集する際に不具合が起きた。原因は、２６日午後４時ごろに終了した７試合を同時処理したことで、午後５時からのＪリーグ１部のガ大阪―甲府戦のライブ配信などに障害が起きたという。従来は多くの試合を同時に編集することはなかったが、「今回はユーザーにすぐハイライトを届け、我々のメリットを実感してもらいたかった」という。 　２５日も一部で同様の障害があったが、その時点では復旧し原因特定に至らなかったという。ラシュトンＣＥＯは「今回の事態は決して許されることではない。二度と起こらぬよう対策した」と話した。今後は複数の映像の同時編集はせず、自動化していた処理も手動で行う。次の試合前日の３日に再度テストするという。','1488491287580',2,'2017-03-02T17:37:39+09:00','2017-03-02'),(1488491288562,'Asahi','Top Stories','南極地域の過去最高気温は１９．８度　世界気象機関発表','Asahi','http://www.asahi.com/articles/ASK31009SK2XUHBI038.html?ref=rss','南極地域の過去最高気温は１９．８度　世界気象機関発表','　国連の世界気象機関（ＷＭＯ、本部スイス・ジュネーブ）は１日、南極地域の各地点における「過去最高気温」を発表した。ＷＭＯの専門家委員会が観測記録から突き止めた。 　南緯６０度以南の南極地域全体での最高気温は、１９８２年１月３０日にシグニー島で観測された１９・８度。南極大陸では２０１５年３月２４日にアルゼンチンの研究施設で観測された１７・５度。標高２５００メートル以上の南極高原では、１９８０年１２月２８日に観測された零下７度だった。 　南極地域での最低気温は、１９８３年７月２１日に旧ソ連のボストーク基地で観測された零下８９・２度（世界最低気温）だった。（ジュネーブ＝松尾一郎）','1488491288562',0,'2017-03-02T03:59:58+09:00','2017-03-02'),(1488491289438,'Asahi','Top Stories','元帝国繊維社長の小花恒雄さん死去','Asahi','http://www.asahi.com/articles/ASK325X2RK32ULFA022.html?ref=rss','元帝国繊維社長の小花恒雄さん死去','　小花恒雄さん（こはな・つねお＝元帝国繊維社長）が１日、肝臓がんで死去、８５歳。通夜は６日午後６時、葬儀は７日正午から東京都練馬区練馬２の１４の１８のねりま浄苑で。喪主は長男恒輔さん。連絡先は同社経営企画部（０３・３２８１・３０２２）。','1488491289438',0,'2017-03-03T02:06:33+09:00','2017-03-02'),(1488491290072,'Asahi','Top Stories','ユーミン「大大大、、親友でした」　かまやつさん死去','Asahi','http://www.asahi.com/articles/ASK326F25K32UCVL01D.html?ref=rss','ユーミン「大大大、、親友でした」　かまやつさん死去','　大ヒット曲「我が良き友よ」で、ムッシュかまやつさんが歌ったのは、げたの音も高らかに闊歩（かっぽ）するバンカラな男性だった。１日に７８歳で亡くなった、かまやつさん。多くの後輩ミュージシャンたちは、この歌に耳を澄ませながら、その実、周囲を安心させるような至って温和な本人の姿を思い浮かべたのではなかったか。ムッシュを悼む声が続く。 ■松任谷由実さんのコメント 　かまやつひろしさんは、１４才のときから知っている、私の恩師であり、大大大、、親友でした。 　風のように生きるおしゃれを、いつも教えてくれました。私もいつか風のように消えて、ムッシュのいるところへ行きたい。 ■歌手・森山良子さんのコメント 　たくさんの良き仲間たちに恵まれ、自分の音楽を大切にギリギリまで活動出来たこと。病室でもギターを弾き唄い、最後まで音楽を肌身離さず、どんな時もムッシュらしい一生だったと思います。  ■俳優・ミュージシャンのミッキー・カーチスさんのコメント 　高校１年くらいからジャズ学校で一緒だった。本当に仲良しだった！　ソロになったり、海外に行ったりで会わない期間もあったけど、また日本に戻って来てからはご飯に行ったりセッションしたり…。最後に会ったのは３年前か？　Ｔｗｉｔｔｅｒでもよく絡んでたなぁ。もう一度会いたかった本当に残念だ！','1488491290072',0,'2017-03-02T20:05:12+09:00','2017-03-02'),(1488491292314,'Asahi','Top Stories','寺沢則忠さん死去　元日本政策投資銀行副総裁','Asahi','http://www.asahi.com/articles/ASK325DRYK32ULFA01K.html?ref=rss','寺沢則忠さん死去　元日本政策投資銀行副総裁','　寺沢則忠さん（てらさわ・のりただ＝元日本政策投資銀行副総裁、元藤和不動産会長、元日本相撲協会監事）が１日死去、７３歳。通夜は６日午後６時、葬儀は７日午前１１時から東京都文京区大塚５の４０の１の護国寺桂昌殿で。喪主は妻るみ子さん。','1488491292314',0,'2017-03-02T19:42:28+09:00','2017-03-02');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-03 13:05:13