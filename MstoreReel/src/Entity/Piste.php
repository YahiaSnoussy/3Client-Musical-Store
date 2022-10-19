<?php

namespace App\Entity;

use App\Controller\PanierController;
use App\Form\PanierType;
use App\Repository\PisteRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
#use PHPMailer\PHPMailer\PHPMailer;
#use PHPMailer\PHPMailer\Exception;
#require __DIR__ .'../../PHPMailer-master/src/PHPMailer.php';
#require __DIR__ .'../../PHPMailer-master/src/SMTP.php';
#require __DIR__ .'../../PHPMailer-master/src/Exception.php';

/**
 * @ORM\Entity(repositoryClass=PisteRepository::class)
 */
class Piste
{
    /**
     * @ORM\Id
     * @ORM\Column(type="string" , length=255)
     */
    private $id;
    /**
     * @ORM\Column(type="string", length=255)
     */
    private $name;

    /**
     * @ORM\Column(type="integer")
     */
    private $userid;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $dlink;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $slink;

    /**
     * @ORM\Column(type="float")
     */
    private $prix;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $email;

    public function getId(): ?string
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    public function getUserid(): ?int
    {
        return $this->userid;
    }

    public function setUserid(int $userid): self
    {
        $this->userid = $userid;

        return $this;
    }

    public function setId(string $id): self
    {
        $this->id = $id;

        return $this;
    }

    public function getDlink(): ?string
    {
        return $this->dlink;
    }

    public function setDlink(string $dlink): self
    {
        $this->dlink = $dlink;

        return $this;
    }

    public function getSlink(): ?string
    {
        return $this->slink;
    }

    public function setSlink(string $slink): self
    {
        $this->slink = $slink;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }
    function get_string_between($string, $start, $end){
        $string = ' ' . $string;
        $ini = strpos($string, $start);
        if ($ini == 0) return '';
        $ini += strlen($start);
        if($end != "" && strpos($string, $end) != 0)$len = strpos($string, $end, $ini) - $ini;
        else $len = strlen($string) - strlen($ini);
        return substr($string, $ini, $len);
    }
    public function thumbnail() : ?string
    {
        return 'http://img.youtube.com/vi/'.$this->getId().'/0.jpg';
    }
    public function fbSharer(){
        return 'https://facebook.com/sharer/sharer.php?u=http%3A%2F%2Fyoutube.com/watch?v='.
            $this->getId();

    }
    public function email ($contenu , $adress)  {
        $mail = new PHPMailer();
        $mail->IsSMTP();
        $mail->Mailer = "smtp";
        $mail->SMTPDebug  = 1;
        $mail->SMTPAuth   = TRUE;
        $mail->SMTPSecure = "tls";
        $mail->Port       = 587;
        $mail->Host       = "smtp.gmail.com";
        $mail->Username   = "yacine.bouguerra@esprit.tn";
        $mail->Password   = "172839456aaa";
        $mail->IsHTML(true);
        $mail->AddAddress($adress, "recipient-name");
        $mail->SetFrom("pidevMstore@gmail.com", "Pidev");
        $mail->AddReplyTo("test", "reply-to-name");
        $mail->AddCC("cc-recipient-email@domain", "cc-recipient-name");
        $mail->Subject = "Opération réussie!";
        $content = "<b> $contenu </b>";
        $mail->MsgHTML($content);
        if(!$mail->Send()) {

            var_dump($mail);

        } else {
            
        }
    }
    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(?string $email): self
    {
        $this->email = $email;

        return $this;
    }

}

